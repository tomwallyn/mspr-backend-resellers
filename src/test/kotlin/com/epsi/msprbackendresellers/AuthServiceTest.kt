package com.epsi.msprbackendresellers
import com.epsi.msprbackendresellers.models.User
import com.epsi.msprbackendresellers.repository.AuthApiClient
import com.epsi.msprbackendresellers.services.NotificationService
import com.epsi.msprbackendresellers.services.impl.AuthServiceImpl
import com.epsi.msprbackendresellers.services.impl.UserServiceImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import reactor.core.publisher.Mono

class AuthServiceImplTest {

    @Mock
    private lateinit var authApiClient: AuthApiClient

    @Mock
    private lateinit var notificationService: NotificationService

    @Mock
    private lateinit var userService: UserServiceImpl

    @InjectMocks
    private lateinit var authService: AuthServiceImpl

    @Test
    fun testLogin() {
        // Arrange
        val username = "testuser"
        val password = "testpassword"
        val token = "testtoken"
        val email = "test@example.com"
        val expectedUser = User(username = username, token = token)

        MockitoAnnotations.openMocks(this)
        `when`(authApiClient.getToken(username, password)).thenReturn(token)
        `when`(userService.geUserEmail(token, username)).thenReturn(email)

        // Act
        val result = authService.login(username, password)

        // Assert
        assertEquals(expectedUser, result.block())
        verify(notificationService).sendMail("tomwallyntel@gmail.com", email, "Authentification QR", token)
    }
}
