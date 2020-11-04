package com.pedrocomitto.drawer.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("participation")
data class Participation(
    @Id
    val email: String,
    val name: String,
    val extraField: String?
)