package com.planit.backend.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "plans")
data class Plan(
    @Id
    val id: String? = null,
    val title: String,
    val description: String,
    val completed: Boolean = false,
    val userId: String,
    val taskIds: List<String> = listOf(),
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val dueDate: LocalDateTime? = null
)
