package com.pedrocomitto.drawer.notification

import com.pedrocomitto.drawer.service.MessageService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.lang.IllegalArgumentException

@Component
class NotificationSender(
    messageServices: List<MessageService>
) {

    private val log = LoggerFactory.getLogger(javaClass)

    private val services = messageServices.associateBy { it.type }

    fun send(token: String, notification: Notification) {
        services[notification.type]?.send(token, notification) ?: throw IllegalArgumentException()
    }
}