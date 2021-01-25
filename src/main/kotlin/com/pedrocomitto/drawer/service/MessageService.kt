package com.pedrocomitto.drawer.service

import com.pedrocomitto.drawer.enumeration.MessageType
import com.pedrocomitto.drawer.notification.Notification

interface MessageService {

    val type: MessageType

    fun send(token: String, notification: Notification)

}