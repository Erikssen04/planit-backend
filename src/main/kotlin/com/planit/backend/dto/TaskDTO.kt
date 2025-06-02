package com.planit.backend.dto

import java.time.LocalDateTime

data class TaskDTO(
    val id: String?,
    val title: String,
    val description: String,
    val completed: Boolean,
    val createdAt: LocalDateTime,
    val dueDate: LocalDateTime?,
    val priority: String?,
    val userId: String? = null,
    val planId: String? = null
)
