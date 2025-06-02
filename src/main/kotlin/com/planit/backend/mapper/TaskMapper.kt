package com.planit.backend.mapper

import com.planit.backend.dto.TaskDTO
import com.planit.backend.model.Task

object TaskMapper {
    fun toDTO(task: Task): TaskDTO =
        TaskDTO(
            id = task.id,
            title = task.title,
            description = task.description,
            completed = task.completed,
            createdAt = task.createdAt,
            dueDate = task.dueDate,
            userId = task.userId,
            planId = task.planId,
            priority = task.priority
        )

    fun toEntity(dto: TaskDTO, userId: String, planId: String?, priority: String?): Task =
        Task(
            id = dto.id,
            title = dto.title,
            description = dto.description,
            completed = dto.completed,
            createdAt = dto.createdAt,
            dueDate = dto.dueDate,
            userId = userId,
            planId = dto.planId,
            priority = dto.priority
        )
}
