package com.example.comento.global.domain;


import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    @Column(updatable = false, unique = true, nullable = false)
    private UUID id;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o==null || this.getClass() != o.getClass()){
            return false;
        }
        return this.id.equals(((BaseEntity) o).id);
    }

    @Override
    public int hashCode(){
        return this.id.hashCode();
    }
}
