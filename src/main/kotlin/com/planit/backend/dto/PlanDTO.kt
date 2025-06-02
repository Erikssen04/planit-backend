package com.planit.backend.dto

import java.time.LocalDateTime

data class PlanDTO(
    val id: String?,
    val title: String,
    val description: String,
    val completed: Boolean,
    val taskIds: List<String>,
    val createdAt: LocalDateTime,
    val dueDate: LocalDateTime?,
    val userId: String? = null
)
