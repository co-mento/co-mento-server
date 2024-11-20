package com.example.comento.solution.service;

import com.example.comento.auth.dto.response.Principal;
import com.example.comento.global.exception.BadRequestException;
import com.example.comento.global.exception.NotFoundException;
import com.example.comento.global.exception.errorcode.ErrorCode;
import com.example.comento.problem.damain.Problem;
import com.example.comento.problem.damain.ProblemCategory;
import com.example.comento.problem.repository.problem.ProblemRepository;
import com.example.comento.solution.dao.ProblemId;
import com.example.comento.solution.dao.SolutionDao;
import com.example.comento.solution.domain.AiFeedback;
import com.example.comento.solution.domain.Solution;
import com.example.comento.solution.dto.response.ProblemIdsResponse;
import com.example.comento.solution.dto.response.SolutionDetailResponse;
import com.example.comento.solution.dto.response.SolutionListResponse;
import com.example.comento.solution.repository.AiFeedbackJpaRepository;
import com.example.comento.solution.repository.SolutionJpaRepository;
import com.example.comento.user.domain.UserProfile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.example.comento.solution.templates.LlmApiTemplate.CODE_REVIEW_SYSTEM_TEXT;
import static com.example.comento.solution.templates.LlmApiTemplate.USER_CODE;

@Service
@RequiredArgsConstructor
@Slf4j
public class SolutionService {
    private final ProblemRepository problemRepository;
    private final SolutionJpaRepository solutionJpaRepository;
    private final ChatClient chatClient;
    private final AiFeedbackJpaRepository aiFeedbackJpaRepository;

    public ProblemIdsResponse userSolvedList(UserProfile userProfile) {
        List<ProblemId> problemIdList = problemRepository.getSolvedProblemList(userProfile);
        return ProblemIdsResponse.from(problemIdList);
    }

    public ProblemIdsResponse userFailedList(UserProfile userProfile) {
        List<ProblemId> problemIdList = problemRepository.getFailedProblemList(userProfile);
        return ProblemIdsResponse.from(problemIdList);
    }

    public ProblemIdsResponse userLikedList(UserProfile userprofile) {
        List<ProblemId> problemIdList = problemRepository.getLikedProblemList(userprofile);
        return ProblemIdsResponse.from(problemIdList);
    }

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

        log.info("response.getResult(): "+ String.valueOf(response.getResult()));
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
