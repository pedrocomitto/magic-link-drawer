package com.pedrocomitto.drawer.document

import com.pedrocomitto.drawer.enumeration.MessageType
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("participation")
data class Participation(
    @Id
    val email: String,
    val name: String,
    val messageType: MessageType,
    val cellphone: String
)