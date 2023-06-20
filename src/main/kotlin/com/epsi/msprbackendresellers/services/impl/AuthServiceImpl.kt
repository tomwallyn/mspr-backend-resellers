package com.epsi.msprbackendresellers.services.impl

import com.epsi.msprbackendresellers.models.User
import com.epsi.msprbackendresellers.repository.AuthApiClient
import com.epsi.msprbackendresellers.services.AuthService
import com.epsi.msprbackendresellers.services.NotificationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class AuthServiceImpl(private val authApiClient: AuthApiClient) : AuthService {
    @Autowired
    lateinit var notificationService: NotificationService

    override fun login(username: String, password: String): Mono<User> {
        val token = authApiClient.getToken(username, password)
        notificationService.sendMail("tomwallyntel@gmail.com", "yoann.collot@epsi.fr", "Authentification QR", token)
        return Mono.just(User(username = username, token = token))
    }
}
