package com.epsi.msprbackendresellers.repository

import com.epsi.msprbackendresellers.models.User
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono

@Component
class AuthApiClient {
    private val baseUrl = "http://localhost:8888/doli/api/index.php/login"

    data class AuthResponse(
        @JsonProperty("success")
        val success: Success
    )

    data class Success(
        @JsonProperty("token")
        val token: String
    )

    fun getToken(username: String, password: String): String {
        val webClient = WebClient.create(baseUrl)
        val responseMono: Mono<AuthResponse> = webClient.get()
            .uri("?login=$username&password=$password")
            .retrieve()
            .bodyToMono(AuthResponse::class.java)

        try {
            val authResponse = responseMono.block()!!
            return authResponse.success.token
        } catch (e: WebClientResponseException) {
            when (e.statusCode) {
                HttpStatus.FORBIDDEN -> throw ResponseStatusException(HttpStatus.FORBIDDEN, "Username or password incorrect")
                else -> throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
            }
        }
    }
}
