package com.example.comento.collection.service;

import com.example.comento.collection.domain.Collection;
import com.example.comento.collection.dto.response.AllCollections;
import com.example.comento.collection.repository.CollectionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectionService {
    private final CollectionJpaRepository collectionJpaRepository;

    public AllCollections getAllCollection(){
        List<Collection> collections = collectionJpaRepository.findAll();
        return AllCollections.from(collections);
    }
}
