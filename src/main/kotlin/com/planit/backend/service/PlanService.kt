package com.planit.backend.service

import com.planit.backend.dto.PlanDTO
import com.planit.backend.mapper.PlanMapper
import com.planit.backend.repository.PlanRepository
import org.springframework.stereotype.Service

@Service
class PlanService(
    private val planRepository: PlanRepository
) {
    fun getAllPlans(): List<PlanDTO> =
        planRepository.findAll().map(PlanMapper::toDTO)

    fun getPlansByUserId(userId: String): List<PlanDTO> =
        planRepository.findByUserId(userId).map(PlanMapper::toDTO)

    fun getPlanById(id: String): PlanDTO? =
        planRepository.findById(id).orElse(null)?.let(PlanMapper::toDTO)

    fun createPlan(dto: PlanDTO): PlanDTO {
        require(dto.userId != null) { "El userId no puede ser nulo" }
        val plan = PlanMapper.toEntity(dto, dto.userId)
        return PlanMapper.toDTO(planRepository.save(plan))
    }

    fun updatePlan(id: String, dto: PlanDTO): PlanDTO? {
        val existing = planRepository.findById(id).orElse(null) ?: return null
        val updated = existing.copy(
            title = dto.title,
            description = dto.description,
            completed = dto.completed,
            taskIds = dto.taskIds,
            dueDate = dto.dueDate
        )
        return PlanMapper.toDTO(planRepository.save(updated))
    }

    fun deletePlan(id: String): Boolean {
        return if (planRepository.existsById(id)) {
            planRepository.deleteById(id)
            true
        } else false
    }

    fun toggleCompleted(id: String): PlanDTO {
        val plan = planRepository.findById(id).orElseThrow { RuntimeException("Task not found") }
        val updated = plan.copy(completed = !plan.completed)
        return planRepository.save(updated).let { PlanMapper.toDTO(it) } // cambia estado a completed
    }
}

