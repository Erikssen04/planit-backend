package com.planit.backend.controller

import com.planit.backend.dto.TaskDTO
import com.planit.backend.service.TaskService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tasks")
class TaskController(
    private val taskService: TaskService
) {

    @GetMapping
    fun getAllTasks(): List<TaskDTO> =
        taskService.getAllTasks()

    @GetMapping("/plan/{planId}")
    fun getTasksByPlanId(@PathVariable planId: String): List<TaskDTO> =
        taskService.getTasksByPlanId(planId)

    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: String): TaskDTO? =
        taskService.getTaskById(id)

    @PostMapping
    fun createTask(@RequestBody dto: TaskDTO): TaskDTO {
        val userId = SecurityContextHolder.getContext().authentication.principal as String
        return taskService.createTask(dto.copy(userId = userId))
    }

    @GetMapping("/me")
    fun getCurrentUserTasks(): List<TaskDTO> {
        val userId = SecurityContextHolder.getContext().authentication.principal as String
        return taskService.getTasksByUserId(userId)
    }


    @PutMapping("/{id}")
    fun updateTask(
        @PathVariable id: String,
        @RequestBody dto: TaskDTO
    ): TaskDTO? {
        return taskService.updateTask(id, dto)
    }

    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable id: String): Boolean =
        taskService.deleteTask(id)

    @PatchMapping("/{id}/toggle")
    fun toggleTaskCompleted(@PathVariable id: String): ResponseEntity<TaskDTO> {
        val updatedTask = taskService.toggleCompleted(id)
        return ResponseEntity.ok(updatedTask)
    }

}
