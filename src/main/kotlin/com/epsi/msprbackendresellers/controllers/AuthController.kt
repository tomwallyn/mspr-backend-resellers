package com.epsi.msprbackendresellers.controllers

import com.epsi.msprbackendresellers.models.User
import com.epsi.msprbackendresellers.services.impl.AuthServiceImpl
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping(value = ["authentication"], produces = [MediaType.APPLICATION_JSON_VALUE])
class AuthController(private val authServiceImpl: AuthServiceImpl) {
    @GetMapping("{username}/{password}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200", description = "Get user token", content = [Content(
                mediaType = "application/json", schema = Schema(implementation = User::class)
            )]
        ),])

    fun getUserToken(@PathVariable username : String, @PathVariable password : String) : Mono<User> = authServiceImpl.login(username, password)
}
