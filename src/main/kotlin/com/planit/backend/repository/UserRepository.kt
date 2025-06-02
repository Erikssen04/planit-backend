package com.planit.backend.repository

import com.planit.backend.model.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String> {
    fun findByUsername(username: String): User?
    fun findByFirebaseUid(firebaseUid: String): User?
}
