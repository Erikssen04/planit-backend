package com.planit.backend.repository

import com.planit.backend.model.Plan
import org.springframework.data.mongodb.repository.MongoRepository

interface PlanRepository : MongoRepository<Plan, String> {
    fun findByUserId(userId: String): List<Plan>
}
