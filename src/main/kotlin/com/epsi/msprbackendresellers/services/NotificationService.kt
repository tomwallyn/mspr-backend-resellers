package com.epsi.msprbackendresellers.services


interface NotificationService {
    fun sendMail(addTo : String, sendTo : String, subject : String, html: String): Boolean
}
