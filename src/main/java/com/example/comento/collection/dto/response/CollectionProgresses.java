package com.example.comento.collection.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CollectionProgresses {
    private List<CollectionProgress> collectionProgresses;
}
