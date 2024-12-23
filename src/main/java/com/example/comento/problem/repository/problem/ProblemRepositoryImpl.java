package com.example.comento.problem.repository.problem;

import com.example.comento.category.domain.QCategory;
import com.example.comento.level.domain.QLevel;
import com.example.comento.problem.damain.*;
import com.example.comento.problem.dto.response.ProblemDetailInformation;
import com.example.comento.problem.dto.response.ProblemPreview;
import com.example.comento.solution.domain.QSolution;
import com.example.comento.solvedstatus.domain.QSolvedStatus;
import com.example.comento.user.domain.UserProfile;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProblemRepositoryImpl implements ProblemCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;
    private final QProblem problem = QProblem.problem;
    private final QLevel level = QLevel.level;
    private final QProblemCategory problemCategory = QProblemCategory.problemCategory;
    private final QProblemCollection problemCollection = QProblemCollection.problemCollection;
    private final QSolution solution = QSolution.solution;
    private final QCategory category = QCategory.category;
    private final QSolvedStatus solvedStatus = QSolvedStatus.solvedStatus;

    @Override
    public Page<ProblemDetailInformation> getProblemPreviews(Pageable pageable,
                                                   UserProfile profileCondition,
                                                   Long levelId,
                                                   UUID categoryId,
                                                   Boolean isSolvedCondition,
                                                   UUID collectionId,
                                                   String keyword) {

        BooleanBuilder predicate = new BooleanBuilder();

        if (levelId != null) {
            predicate.and(problem.level.id.eq(levelId));
        }
        if (categoryId != null) {
            predicate.and(problemCategory.category.id.eq(categoryId));
        }


        if (isSolvedCondition != null && profileCondition != null) {
            if(isSolvedCondition){
                predicate.and(solution.userProfile.eq(profileCondition)).and(solution.isCorrect.eq(isSolvedCondition));
            }

            if(!isSolvedCondition){
                //해당 유저의 solvedStatus=true 인 문제 제외. or로 problem 에 solvedStatus 자체가 없는 것도 포함.
                predicate.and(solvedStatus.userProfile.eq(profileCondition).and(solvedStatus.flag.eq(true)).not()
                        .or(solvedStatus.isNull()));
            }
        }
        if (collectionId != null) {
            predicate.and(problemCollection.collection.id.eq(collectionId));
        }
        if (keyword != null) {
            predicate.and(problem.title.contains(keyword));
        }

        BooleanExpression hasSolved = Expressions.asBoolean(false);

        if (profileCondition != null) {
            hasSolved = JPAExpressions.select(solution.count())
                    .from(solution)
                    .where(solution.problem.eq(problem)
                            .and(solution.userProfile.eq(profileCondition))
                            .and(solution.isCorrect.eq(true)))
                    .exists();
        }


        JPAQuery<ProblemDetailInformation> query = jpaQueryFactory.select(Projections.constructor(ProblemDetailInformation.class,
                        problem,
                        hasSolved))
                .from(problem)
                .leftJoin(problem.problemCategoryList, problemCategory)
                .leftJoin(problem.problemCollectionList, problemCollection)
                .leftJoin(problem.solutionList, solution)
                .leftJoin(problem.solvedStatuses, solvedStatus)
                .where(predicate)
                .groupBy(problem.id)
                .orderBy(problem.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
//                .fetch();

        long total = query.fetchCount();
        List<ProblemDetailInformation> problemPreviews = query.fetch();
        return new PageImpl<>(problemPreviews, pageable, total);
    }

}
