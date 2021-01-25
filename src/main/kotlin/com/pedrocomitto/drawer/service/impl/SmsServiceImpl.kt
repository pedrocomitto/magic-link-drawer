package com.pedrocomitto.drawer.service.impl

import com.pedrocomitto.drawer.enumeration.MessageType
import com.pedrocomitto.drawer.notification.Notification
import com.pedrocomitto.drawer.service.MessageService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SmsServiceImpl : MessageService {

    private val log = LoggerFactory.getLogger(javaClass)

    override val type: MessageType = MessageType.SMS

    override fun send(token: String, notification: Notification) {
        log.info("M=send, I=sending sms, cellphone=${notification.recipient}")
    }

}