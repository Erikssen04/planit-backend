package com.planit.backend.repository

import com.planit.backend.model.Task
import org.springframework.data.mongodb.repository.MongoRepository

interface TaskRepository : MongoRepository<Task, String> {
    fun findByUserId(userId: String): List<Task>
    fun findByPlanId(planId: String): List<Task>
}
