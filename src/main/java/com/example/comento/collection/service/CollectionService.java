package com.example.comento.collection.service;

import com.example.comento.auth.dto.response.Principal;
import com.example.comento.collection.domain.Collection;
import com.example.comento.collection.dto.response.AllCollections;
import com.example.comento.collection.dto.response.CollectionProgress;
import com.example.comento.collection.dto.response.CollectionProgresses;
import com.example.comento.collection.repository.CollectionJpaRepository;
import com.example.comento.problem.damain.ProblemCollection;
import com.example.comento.solvedstatus.service.SolvedStatusService;
import com.example.comento.user.domain.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectionService {
    private final CollectionJpaRepository collectionJpaRepository;
    private final SolvedStatusService solvedStatusService;

    public AllCollections getAllCollection(){
        List<Collection> collections = collectionJpaRepository.findAll();
        return AllCollections.from(collections);
    }

    public CollectionProgresses getAllCollectionProgresses(Principal principal){
        List<Collection> collections = collectionJpaRepository.findAll();
        UserProfile profile = principal.getProfile();
        return new CollectionProgresses(collections.stream()
                .map(collection -> getCollectionProgress(collection, profile))
                .toList());
    }

    private CollectionProgress getCollectionProgress(Collection collection, UserProfile userProfile){
        List<ProblemCollection> problemCollections = collection.getProblemCollectionList();
        int problemCount = problemCollections.size();
        long userSolvedCount = problemCollections.stream()
                .map(ProblemCollection::getProblem)
                .filter(problem-> solvedStatusService.isSolvedStatusTrue(userProfile, problem))
                .count();
        int progress = Math.round(((float) userSolvedCount / problemCount) * 100);
        return CollectionProgress.from(collection, progress);

    }
}
