package com.epsi.msprbackendresellers.models

data class User(
    var username : String? = null,
    var token : String? = null,
    var password : String? = null,
    var email: String?= null
) {

}
