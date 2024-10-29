package com.example.comento.auth.dto.response;


import com.example.comento.user.domain.User;
import com.example.comento.user.domain.UserProfile;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class Principal {
    private final User user;
    private final UserProfile profile;
}
