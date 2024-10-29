package com.example.comento.auth.util.authentication;

import com.example.comento.auth.dto.response.Principal;
import com.example.comento.user.domain.User;
import com.example.comento.user.domain.UserProfile;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;


@Getter
@Component
@RequestScope
public class AuthenticationContext {
    private Principal principal;

    public void setPrincipal(User user, UserProfile profile) {
        this.principal = new Principal(user, profile);
    }
}
