package com.epsi.msprbackendresellers

import com.epsi.msprbackendresellers.models.User
import com.epsi.msprbackendresellers.repository.AuthApiClient
import com.epsi.msprbackendresellers.services.AuthService
import com.epsi.msprbackendresellers.services.impl.AuthServiceImpl
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import reactor.test.StepVerifier

@ExtendWith(MockitoExtension::class)
class AuthServiceTest {

    @Mock
    private lateinit var authApiClient: AuthApiClient

    @Test
    fun `login should return user with token`() {
        // Given
        val authService: AuthService = AuthServiceImpl(authApiClient)
        val username = "john.doe"
        val password = "password"
        val expectedToken = "your_token"

        `when`(authApiClient.getToken(username, password)).thenReturn(expectedToken)

        // When
        val result = authService.login(username, password)

        // Then
        StepVerifier.create(result)
                .expectNext(User(username = username, token = expectedToken))
                .verifyComplete()
    }
}
