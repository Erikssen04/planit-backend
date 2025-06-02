package com.planit.backend.controller

import com.planit.backend.dto.PlanDTO
import com.planit.backend.dto.TaskDTO
import com.planit.backend.service.PlanService
import com.planit.backend.service.TaskService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/plans")
class PlanController(
    private val planService: PlanService,
    private val taskService: TaskService
) {

    @GetMapping
    fun getAllPlans(): List<PlanDTO> =
        planService.getAllPlans()

    @GetMapping("/plan/{planId}")
    fun getTasksByPlanId(@PathVariable planId: String): List<TaskDTO> =
        taskService.getTasksByPlanId(planId)

    @GetMapping("/{id}")
    fun getPlanById(@PathVariable id: String): PlanDTO? =
        planService.getPlanById(id)

    @PostMapping
    fun createPlan(@RequestBody dto: PlanDTO): PlanDTO {
        val userId = SecurityContextHolder.getContext().authentication.principal as String
        return planService.createPlan(dto.copy(userId = userId))
    }

    @GetMapping("/me")
    fun getCurrentUserPlans(): List<PlanDTO> {
        val userId = SecurityContextHolder.getContext().authentication.principal as String
        return planService.getPlansByUserId(userId)
    }

    @PutMapping("/{id}")
    fun updatePlan(
        @PathVariable id: String,
        @RequestBody dto: PlanDTO
    ): PlanDTO? =
        planService.updatePlan(id, dto)

    @DeleteMapping("/{id}")
    fun deletePlan(@PathVariable id: String): Boolean =
        planService.deletePlan(id)

    @PatchMapping("/{id}/toggle")
    fun togglePlanCompleted(@PathVariable id: String): ResponseEntity<PlanDTO> {
        val updatedPlan = planService.toggleCompleted(id)
        return ResponseEntity.ok(updatedPlan)
    }
}
