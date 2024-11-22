package com.example.comento.collection.dto.response;


import com.example.comento.collection.domain.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AllCollections {
    private List<CollectionResponse> collections;

    public static AllCollections from(List<Collection> collections){
        return new AllCollections(collections.stream()
                .map(CollectionResponse::from)
                .toList());
    }
}
