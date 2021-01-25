package com.pedrocomitto.drawer.service.impl

import com.pedrocomitto.drawer.enumeration.MessageType
import com.pedrocomitto.drawer.notification.Notification
import com.pedrocomitto.drawer.service.MessageService
import org.slf4j.LoggerFactory
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailServiceImpl(
    private val javaMailSender: JavaMailSender
) : MessageService {

    private val log = LoggerFactory.getLogger(javaClass)

    override val type: MessageType = MessageType.EMAIL

    override fun send(token: String, notification: Notification) {
        log.info("M=send, I=sending email, recipient=${notification.recipient}")

        val msg = SimpleMailMessage()

        msg.setFrom("\${spring.email.username}")
        msg.setTo(notification.recipient)
        msg.setSubject("Please confirm your participation")
        msg.setText("Click the link below to confirm your participation: \n\n http://localhost:8080/participation/confirm?token=$token")

        javaMailSender.send(msg)
    }

}