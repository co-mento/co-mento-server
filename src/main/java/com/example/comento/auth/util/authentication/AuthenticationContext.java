package com.example.comento.auth.util.authentication;

import com.example.comento.user.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Setter
@Getter
@Component
@RequestScope
public class AuthenticationContext {
    private User principal;
}
