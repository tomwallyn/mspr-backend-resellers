package com.epsi.msprbackendresellers.services.impl

import com.epsi.msprbackendresellers.models.User
import com.epsi.msprbackendresellers.repository.AuthApiClient
import com.epsi.msprbackendresellers.services.AuthService
import com.epsi.msprbackendresellers.services.NotificationService
import com.epsi.msprbackendresellers.services.UserService
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class AuthServiceImpl(private val authApiClient: AuthApiClient, private val userService: UserService, private val notificationService: NotificationService) : AuthService {

    override fun login(username: String, password: String): Mono<User> {
        val token = authApiClient.getToken(username, password)
        val email = userService.geUserEmail(token, username)
        notificationService.sendMail("tomwallyntel@gmail.com", email, "Authentification QR", token)
        return Mono.just(User(username = username, token = token))
    }
}
