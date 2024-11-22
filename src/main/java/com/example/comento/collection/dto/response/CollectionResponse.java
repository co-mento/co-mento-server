package com.example.comento.collection.dto.response;

import com.example.comento.collection.domain.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor()
public class CollectionResponse {
    private UUID id;
    private String name;
    private String description;

    public static CollectionResponse from(Collection collection){
        return new CollectionResponse(collection.getId(), collection.getName(), collection.getDescription());
    }
}
