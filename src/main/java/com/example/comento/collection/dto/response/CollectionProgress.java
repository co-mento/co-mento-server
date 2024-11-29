package com.example.comento.collection.dto.response;

import com.example.comento.collection.domain.Collection;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CollectionProgress {
    private UUID id;
    private String name;
    private String description;
    private int Progress;

    public static CollectionProgress from(Collection collection, int progress){
        return new CollectionProgress(collection.getId(),
                collection.getName(),
                collection.getDescription(),
                progress);
    }

}
