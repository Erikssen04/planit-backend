package com.planit.backend.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "users")
data class User(
    @Id
    val id: String? = null,
    val username: String,
    val email: String,
    val firebaseUid: String,
    val createdAt: LocalDateTime = LocalDateTime.now()
)