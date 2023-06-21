package com.epsi.msprbackendresellers.services.impl

import com.epsi.msprbackendresellers.repository.UserApiClient
import com.epsi.msprbackendresellers.services.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userApiClient: UserApiClient) : UserService {

    override fun geUserEmail(token: String, login: String):  String {
        return userApiClient.getUserEmail(token, login).block()!!.email!!
    }
}
