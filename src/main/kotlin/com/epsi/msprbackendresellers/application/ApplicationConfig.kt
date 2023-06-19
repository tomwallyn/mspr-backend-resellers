package com.epsi.msprbackendresellers.application

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.boot.SpringApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient


@Configuration
class ApplicationConfig {
    @Bean
    fun restTemplate(): RestTemplate? {
        return RestTemplate()
    }
}

@Configuration
class WebClientConfig {
    @Bean
    fun webClient(): WebClient {
        return WebClient.builder()
            .baseUrl("http://localhost:8888/doli/api/index.php")
            .build()
    }
}


object SpringMongoConnectionViaPropertiesApp {
    @JvmStatic
    fun main(args: Array<String>) {
        SpringApplication.run(SpringMongoConnectionViaPropertiesApp::class.java, *args)
    }
}

@Configuration
class OpenApiConfig {
    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .components(Components())
            .info(
                Info()
                .title("My Fantastic API")
                .description("This is the API documentation for my fantastic awesome incredible software."))
    }
}

