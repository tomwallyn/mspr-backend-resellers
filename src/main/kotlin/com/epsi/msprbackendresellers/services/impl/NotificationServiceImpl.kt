package com.epsi.msprbackendresellers.services.impl

import com.epsi.msprbackendresellers.services.NotificationService
import com.sendgrid.Method
import com.sendgrid.Request
import com.sendgrid.Response
import com.sendgrid.SendGrid
import com.sendgrid.helpers.mail.Mail
import com.sendgrid.helpers.mail.objects.Content
import com.sendgrid.helpers.mail.objects.Email
import org.springframework.stereotype.Service
import java.io.IOException

@Service
class NotificationServiceImpl : NotificationService {
    override fun sendMail(addTo: String, sendTo: String, subject_mail: String, token: String): Response {
        val from = Email(addTo)
        val subject = subject_mail
        val to = Email(sendTo)
        val content = Content("text/plain", "https://barcode.tec-it.com/fr/QRCode?data=${token}")
        val mail = Mail(from, subject, to, content)

        val sg = SendGrid("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")
        val request = Request()
        try {
            request.setMethod(Method.POST)
            request.setEndpoint("mail/send")
            request.setBody(mail.build())
            val response: Response = sg.api(request)
            System.out.println(response.getStatusCode())
            System.out.println(response.getBody())
            System.out.println(response.getHeaders())
            return response
        } catch (ex: IOException) {
            throw ex
        }
    }
}
