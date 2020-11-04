package com.pedrocomitto.drawer.service

import org.slf4j.LoggerFactory
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val javaMailSender: JavaMailSender
) {

    private val log = LoggerFactory.getLogger(javaClass)

    fun send(token: String, recipient: String) {
        log.info("sending email, email=$recipient")

        val msg = SimpleMailMessage()

        msg.setFrom("\${spring.email.username}")
        msg.setTo(recipient)
        msg.setSubject("Please confirm your participation")
        msg.setText("Click the link below to confirm your participation: \n\n http://localhost:8080/participation/confirm?token=$token")

        javaMailSender.send(msg)
    }

}