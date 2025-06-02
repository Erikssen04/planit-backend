package com.planit.backend.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "tasks")
data class Task(
    @Id
    val id: String? = null,
    val title: String,
    val description: String,
    val completed: Boolean = false,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val dueDate: LocalDateTime? = null,
    val userId: String,
    val planId: String? = null,
    val priority: String? = null
)
