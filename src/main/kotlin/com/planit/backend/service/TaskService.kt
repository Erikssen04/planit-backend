package com.planit.backend.service

import com.planit.backend.dto.TaskDTO
import com.planit.backend.mapper.TaskMapper
import com.planit.backend.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class TaskService(
    private val taskRepository: TaskRepository
) {
    fun getAllTasks(): List<TaskDTO> =
        taskRepository.findAll().map(TaskMapper::toDTO)

    fun getTasksByUserId(userId: String): List<TaskDTO> =
        taskRepository.findByUserId(userId).map(TaskMapper::toDTO)

    fun getTasksByPlanId(planId: String): List<TaskDTO> =
        taskRepository.findByPlanId(planId).map(TaskMapper::toDTO)

    fun getTaskById(id: String): TaskDTO? =
        taskRepository.findById(id).orElse(null)?.let(TaskMapper::toDTO)

    fun createTask(dto: TaskDTO): TaskDTO {
        require(dto.userId != null) { "El userId no puede ser nulo" }
        val task = TaskMapper.toEntity(dto, dto.userId!!, null, dto.priority)
        return TaskMapper.toDTO(taskRepository.save(task))
    }

    fun updateTask(id: String, dto: TaskDTO): TaskDTO? {
        val existing = taskRepository.findById(id).orElse(null) ?: return null
        val updated = existing.copy(
            title = dto.title,
            description = dto.description,
            completed = dto.completed,
            dueDate = dto.dueDate,
            priority = dto.priority,
            planId = dto.planId
        )
        return TaskMapper.toDTO(taskRepository.save(updated))
    }

    fun deleteTask(id: String): Boolean {
        return if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id)
            true
        } else false
    }

    fun toggleCompleted(id: String): TaskDTO {
        val task = taskRepository.findById(id).orElseThrow { RuntimeException("Task not found") }
        val updated = task.copy(completed = !task.completed)
        return taskRepository.save(updated).let { TaskMapper.toDTO(it) }
    }

}

