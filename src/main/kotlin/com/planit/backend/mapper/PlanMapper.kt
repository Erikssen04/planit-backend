package com.planit.backend.mapper

import com.planit.backend.dto.PlanDTO
import com.planit.backend.model.Plan

object PlanMapper {
    fun toDTO(plan: Plan): PlanDTO =
        PlanDTO(
            id = plan.id,
            title = plan.title,
            description = plan.description,
            completed = plan.completed,
            taskIds = plan.taskIds,
            createdAt = plan.createdAt,
            dueDate = plan.dueDate,
            userId = plan.userId
        )

    fun toEntity(dto: PlanDTO, userId: String): Plan =
        Plan(
            id = dto.id,
            title = dto.title,
            description = dto.description,
            completed = dto.completed,
            taskIds = dto.taskIds,
            createdAt = dto.createdAt,
            dueDate = dto.dueDate,
            userId = userId
        )
}
