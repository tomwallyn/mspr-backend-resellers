package com.epsi.msprbackendresellers.repository

import com.epsi.msprbackendresellers.models.User
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class UserApiClient(private val webClient: WebClient) {

    fun getUserEmail(token: String, login: String): Mono<User> {
        val url = "/users/login/$login"
        return webClient.get()
            .uri(url)
            .header("DOLAPIKEY", token)
            .retrieve()
            .bodyToMono(User::class.java)
    }
}
