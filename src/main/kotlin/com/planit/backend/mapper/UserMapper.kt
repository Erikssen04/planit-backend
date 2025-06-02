package com.planit.backend.mapper

import com.planit.backend.dto.UserDTO
import com.planit.backend.model.User

object UserMapper {
    fun toDTO(user: User): UserDTO =
        UserDTO(
            id = user.id,
            username = user.username,
            email = user.email,
            firebaseUid = user.firebaseUid
        )

    fun toEntity(dto: UserDTO): User =
        User(
            id = dto.id,
            username = dto.username,
            email = dto.email,
            firebaseUid = dto.firebaseUid
        )
}



