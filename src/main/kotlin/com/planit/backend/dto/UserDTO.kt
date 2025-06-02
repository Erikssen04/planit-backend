package com.planit.backend.dto

data class UserDTO(
    val id: String?,
    val username: String,
    val email: String,
    val firebaseUid: String
)

