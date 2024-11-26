package com.example.comento.solution.service;

import com.example.comento.auth.dto.response.Principal;
import com.example.comento.global.exception.BadRequestException;
import com.example.comento.global.exception.ConflictException;
import com.example.comento.global.exception.InternetException;
import com.example.comento.global.exception.NotFoundException;
import com.example.comento.global.exception.errorcode.ErrorCode;
import com.example.comento.problem.damain.Problem;
import com.example.comento.problem.damain.ProblemCategory;
import com.example.comento.problem.damain.TestCase;
import com.example.comento.problem.dto.request.TestCaseRequest;
import com.example.comento.problem.repository.problem.ProblemRepository;
import com.example.comento.problem.service.ProblemService;
import com.example.comento.solution.dao.ProblemId;
import com.example.comento.solution.dao.SolutionDao;
import com.example.comento.solution.domain.AiFeedback;
import com.example.comento.solution.domain.Language;
import com.example.comento.solution.domain.Solution;
import com.example.comento.solution.dto.request.GradingServerRequest;
import com.example.comento.solution.dto.request.SolutionRequest;
import com.example.comento.solution.dto.response.GradingServerResponse;
import com.example.comento.solution.dto.response.ProblemIdsResponse;
import com.example.comento.solution.dto.response.SolutionDetailResponse;
import com.example.comento.solution.dto.response.SolutionListResponse;
import com.example.comento.solution.repository.AiFeedbackJpaRepository;
import com.example.comento.solution.repository.SolutionJpaRepository;
import com.example.comento.solvedstatus.service.SolvedStatusService;
import com.example.comento.user.domain.User;
import com.example.comento.user.domain.UserProfile;
import com.example.comento.user.repository.UserProfileJpaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.comento.solution.templates.LlmApiTemplate.CODE_REVIEW_SYSTEM_TEXT;
import static com.example.comento.solution.templates.LlmApiTemplate.USER_CODE;

@Service
@RequiredArgsConstructor
@Slf4j
public class SolutionService {

    private final ProblemService problemService;
    private final SolvedStatusService solvedStatusService;

//    private final ProblemRepository problemRepository;
    private final SolutionJpaRepository solutionJpaRepository;
    private final AiFeedbackJpaRepository aiFeedbackJpaRepository;
    private final UserProfileJpaRepository userProfileJpaRepository;

    private final WebClient.Builder webClientBuilder;
    private final ChatClient chatClient;


    private final static String TEST_CASE_PARSER = "\\{\"testCases\":(\\[.*?])}";
    private final static String CORRECT_STATUS = "Accepted";

    @Value("${submit.site.url}")
    private String submitSiteUri;

    public SolutionListResponse findAllAboutProblem(Long problemId, int page, int size, UUID userProfileId) {
        Pageable pageable = PageRequest.of(page, size);

        if (userProfileId == null && problemId == null) {
            Page<SolutionDao> solutionDaos = solutionJpaRepository.findAllSolution(pageable);
            return SolutionListResponse.from(solutionDaos);
        }
        if (userProfileId == null) {
            Page<SolutionDao> solutionDaos = solutionJpaRepository.findAllByProblemId(problemId, pageable);
            return SolutionListResponse.from(solutionDaos);
        }
        if (problemId == null) {
            Page<SolutionDao> solutionDaos = solutionJpaRepository.findAllByProfileId(userProfileId, pageable);
            return SolutionListResponse.from(solutionDaos);
        }

        Page<SolutionDao> solutionDaos = solutionJpaRepository.findAllByProblemIdAndUserProfileId(problemId, userProfileId, pageable);
        return SolutionListResponse.from(solutionDaos);
    }

    public SolutionDetailResponse findSolution(UUID solutionId) {
        Solution solution = findById(solutionId);
        return SolutionDetailResponse.from(solution);
    }

    public Solution findById(UUID solutionId){
        return solutionJpaRepository.findById(solutionId).orElseThrow(()-> new NotFoundException(ErrorCode.SOLUTION_NOT_FOUND));
    }

    @Transactional
    public SolutionDetailResponse getFailedAiReview(UUID solutionId, Principal principal){
        Solution solution = findById(solutionId);
        UserProfile profile = principal.getProfile();
        validAiReviewRequest(solution, profile);

        Problem problem = solution.getProblem();

        generateAiReview(problem, solution);
        return SolutionDetailResponse.from(solution);
    }

    @Transactional
    public SolutionDetailResponse solveProblem(Principal principal,
                                               Long problemId,
                                               SolutionRequest solutionRequest){
        Problem problem = problemService.findById(problemId);
        UserProfile userProfile = principal.getProfile();
        List<TestCaseRequest> testCases = problem.getTestCases().stream()
                .map(TestCaseRequest::from)
                .toList();
        Language language = Language.find(solutionRequest.getLanguage());
        GradingServerResponse gradingServerResponse = checkTestCases(testCases, problem, language, solutionRequest.getCode());
        Solution newSolution = registerSolution(userProfile, solutionRequest.getCode(), problem, language, gradingServerResponse);
        if(newSolution.isCorrect()){
            userProfile.increaseExperience(problem.getLevel().getExperience());
            generateAiReview(problem, newSolution);
            userProfileJpaRepository.save(userProfile);
        }else{
            solutionJpaRepository.save(newSolution);
        }
        solvedStatusService.registerSolvedStatus(userProfile, problem, newSolution.isCorrect());
        return SolutionDetailResponse.from(newSolution);
    }

    private Solution registerSolution(UserProfile profile, String code, Problem problem, Language language, GradingServerResponse serverResponse){
        Boolean isCorrect = serverResponse.isAllSuccess();
        String errorReason = CORRECT_STATUS;
        if (!isCorrect){
            errorReason = serverResponse.getResults().stream()
                    .filter(result -> !result.getStatus().equals(CORRECT_STATUS))
                    .findFirst()
                    .orElseGet(null)
                    .getStatus();
        }
        return Solution.builder()
                .userProfile(profile)
                .problem(problem)
                .language(language.getName())
                .isCorrect(isCorrect)
                .code(code)
                .memory(serverResponse.getMaxMemory())
                .time(serverResponse.getMaxTime())
                .errorReason(errorReason)
                .build();
    }

    private GradingServerResponse checkTestCases(List<TestCaseRequest> testCases,
                                Problem problem,
                                Language language,
                                String code){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String testCaseJson = objectMapper.writeValueAsString(testCases);
            return sendToGradingServer(testCaseJson, problem, code, language);

        } catch (JsonProcessingException e) {
            throw new ConflictException(ErrorCode.TEST_CASE_PARSE_ERROR);
        }
    }

    private GradingServerResponse sendToGradingServer(String testCaseJson, Problem problem, String code, Language language){
        GradingServerRequest serverRequest = GradingServerRequest.builder()
                .source_code(code)
                .language_id(language.getId())
                .test_case(testCaseJson)
                .cpu_time_limit(String.valueOf(problem.getTimeLimit()))
                .memory_limit(String.valueOf(problem.getMemoryLimit()))
                .build();

        log.info("serverRequest: "+serverRequest.toString());
        return webClientBuilder
                .baseUrl(submitSiteUri)
                .build()
                .post()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(serverRequest)
                .retrieve()
                .bodyToMono(GradingServerResponse.class)
                .block();
    }

    private void validAiReviewRequest(Solution solution, UserProfile profile){
        if (solution.getAiFeedback()==null && !solution.isCorrect() && solution.getUserProfile().getId().equals(profile.getId())){
            return;
        }
        throw new BadRequestException(ErrorCode.INVALID_AI_REVIEW);
    }

    private void generateAiReview(Problem problem, Solution solution){
        Message userMessage = createUserMessage(solution);
        Message systemMessage = createSystemMessage(problem);

        Prompt prompt = new Prompt(List.of(userMessage, systemMessage));
        ChatResponse response = chatClient
                .prompt(prompt)
                .call()
                .chatResponse();

        AiFeedback aiFeedback = AiFeedback.builder()
                .solution(solution)
                .content(String.valueOf(response.getResult().getOutput().getContent()))
                .build();

        solution.registerAiFeedBack(aiFeedback);

        solutionJpaRepository.save(solution);
//        aiFeedbackJpaRepository.save(aiFeedback);
    }

    private Message createUserMessage(Solution solution){
        return new UserMessage(
                String.format(USER_CODE, solution.isCorrect(), solution.getCode()));
    }

    private Message createSystemMessage(Problem problem){
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(CODE_REVIEW_SYSTEM_TEXT);

        StringBuffer categoriesBuffer = new StringBuffer();

        List<ProblemCategory> problemCategoryList = problem.getProblemCategoryList();
        problemCategoryList
                .forEach(problemCategory -> categoriesBuffer.append(", " + problemCategory.getCategory().getName()));

        return systemPromptTemplate.createMessage(Map.of(
                "title", problem.getTitle(),
                "content", problem.getContent(),
                "inputExplain", problem.getInputExplain(),
                "outputExplain", problem.getOutputExplain(),
                "inputExample", problem.getInputExample(),
                "outputExample", problem.getOutputExample(),
                "category", categoriesBuffer));
    }
}
