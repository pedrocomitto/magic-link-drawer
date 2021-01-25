package com.pedrocomitto.drawer.notification

import com.pedrocomitto.drawer.document.Participation
import com.pedrocomitto.drawer.enumeration.MessageType

data class Notification(
    val type: MessageType,
    val recipient: String
) {

    companion object {
        fun of(participation: Participation): Notification {
            return when (participation.messageType) {
                MessageType.EMAIL -> Notification(MessageType.EMAIL, participation.email)
                MessageType.SMS -> Notification(MessageType.SMS, participation.cellphone)
            }
        }
    }
}
