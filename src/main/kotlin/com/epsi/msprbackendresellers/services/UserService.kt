package com.epsi.msprbackendresellers.services
interface UserService {
    fun geUserEmail(token: String, login: String) : String
}
