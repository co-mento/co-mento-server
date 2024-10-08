package com.example.comento.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Co-Mento API")
                        .version("v1.0.0")
                        .description("2024년도 2학기 캡스톤 디자인2 Co-Mento API 문서입니다.")
                );
    }
}
