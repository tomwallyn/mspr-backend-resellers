package com.epsi.msprbackendresellers.services

import com.sendgrid.Response

interface NotificationService {
    fun sendMail(addTo : String, sendTo : String, subject : String, html: String) : Response
}
