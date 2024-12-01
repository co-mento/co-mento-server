package com.example.comento.user.domain;

import com.example.comento.global.domain.UuidTypeBaseEntity;
import com.example.comento.user.domain.enums.Social;
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

//    @Column(nullable = false, unique = true)
//    private String email;

    @Column(nullable = false, updatable = false)
    private String salt;

    @PrimaryKeyJoinColumn
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private UserProfile userProfile;

    @Column
    private Social social;

    public User (String userId, String password, String salt, String name, Social social){
        this.userId = userId;
        this.password = password;
        this.salt = salt;
        this.userProfile = new UserProfile(this, name);
        this.social = social;
    }

}
