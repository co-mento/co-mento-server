package com.example.comento.user.domain;

import com.example.comento.global.domain.UuidTypeBaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends UuidTypeBaseEntity {

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, updatable = false)
    private String salt;

    @PrimaryKeyJoinColumn
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private UserProfile userProfile;

    public User (String userId, String password, String salt, String email, String name){
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.salt = salt;
        this.userProfile = new UserProfile(this, name);
    }

}
