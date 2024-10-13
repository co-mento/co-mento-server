package com.example.comento.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String API_NAME = "Co-Mento API";
    private static final String API_VERSION = "v1.0.0";
    private static final String API_DESCRIPTION = "2024년도 2학기 캡스톤 디자인2 Co-Mento API 문서입니다.";


    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title(API_NAME)
                        .version(API_VERSION)
                        .description(API_DESCRIPTION)
                );
    }
}
