package com.epsi.msprbackendresellers.services.impl

import com.epsi.msprbackendresellers.services.NotificationService
import io.github.g0dkar.qrcode.QRCode
import org.simplejavamail.api.email.Email
import org.simplejavamail.email.EmailBuilder
import org.simplejavamail.mailer.MailerBuilder
import org.springframework.stereotype.Service
import java.io.FileOutputStream
import java.nio.file.Files
import java.nio.file.Path
import java.util.*


@Service
class NotificationServiceImpl : NotificationService {
    override fun sendMail(addTo: String, sendTo: String, subjectMail: String, token: String): Boolean{
        getQRCode(token)
        // java.nio.file.Path path
        val path = Path.of("codeqr.png")
        val bytes = Files.readAllBytes(path)
        val base64EncodedImageBytes: String = Base64.getEncoder().encodeToString(bytes)


        val email: Email = EmailBuilder.startingBlank()
                .from("mspr", addTo)
                .to("", sendTo)
                .withSubject(subjectMail)
                .withHTMLText("<div>\n" +
                        "  <p>Your QR authentication code</p>\n" +
                        "  <img src=\"data:image/png;base64," + base64EncodedImageBytes + " \" alt=\"Red dot\" />\n" +
                        "</div>")
                .buildEmail()

        var mailer = MailerBuilder
                .withSMTPServer("smtp.freesmtpservers.com", 25).buildMailer()
        mailer.sendMail(email);
        return true
    }

    fun getQRCode(token: String) {
        FileOutputStream("codeqr.png").use {
            QRCode(token)
                    .render()
                    .writeImage(it)
        }
    }
}
