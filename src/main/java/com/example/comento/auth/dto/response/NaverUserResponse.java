package com.example.comento.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class NaverUserResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("age")
    private String age;

    @JsonProperty("mobile")
    private String mobile;

    @JsonProperty("mobile_e164")
    private String mobileE164;

    @JsonProperty("name")
    private String name;
}
