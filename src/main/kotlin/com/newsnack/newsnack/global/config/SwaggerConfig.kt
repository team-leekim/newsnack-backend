package com.newsnack.newsnack.global.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun openAPI(): OpenAPI = OpenAPI()
        .info(Info()
            .title("Newsnack API Server")
            .description("뉴스낵 API 명세서입니다.")
            .version("v1.0.0"))
}
