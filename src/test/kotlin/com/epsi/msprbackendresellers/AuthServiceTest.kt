package com.epsi.msprbackendresellers

import com.epsi.msprbackendresellers.models.User
import com.epsi.msprbackendresellers.repository.AuthApiClient
import com.epsi.msprbackendresellers.services.AuthService
import com.epsi.msprbackendresellers.services.NotificationService
import com.epsi.msprbackendresellers.services.impl.AuthServiceImpl
import com.epsi.msprbackendresellers.services.impl.NotificationServiceImpl
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.any
import org.mockito.Mockito.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import reactor.test.StepVerifier

@ExtendWith(MockitoExtension::class)
class AuthServiceImplTest {

    private val authApiClient: AuthApiClient = mock(AuthApiClient::class.java)
    private val notificationService: NotificationService = mock(NotificationService::class.java)
    private val authService = AuthServiceImpl(authApiClient).apply { notificationService = this@AuthServiceImplTest.notificationService }

    @Test
    fun `login should return user with token and send email notification`() {
        // Arrange
        val username = "testUser"
        val password = "testPassword"
        val expectedToken = "testToken"
        val expectedUser = User(username = username, token = expectedToken)

        `when`(authApiClient.getToken(username, password)).thenReturn(expectedToken)

        // Act
        val result = authService.login(username, password)

        // Assert
        StepVerifier.create(result)
            .expectNext(expectedUser)
            .expectComplete()
            .verify()

        verify(notificationService).sendMail("tomwallyntel@gmail.com", "yoann.collot@epsi.fr", "Authentification QR", expectedToken)
    }
}
