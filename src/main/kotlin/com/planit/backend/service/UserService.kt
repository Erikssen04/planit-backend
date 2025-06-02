package com.planit.backend.service

import com.planit.backend.dto.UserDTO
import com.planit.backend.mapper.UserMapper
import com.planit.backend.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun getAllUsers(): List<UserDTO> =
        userRepository.findAll().map(UserMapper::toDTO)

    fun getUserById(id: String): UserDTO? =
        userRepository.findById(id).orElse(null)?.let(UserMapper::toDTO)

    fun createUser(dto: UserDTO): UserDTO {
        val user = UserMapper.toEntity(dto)
        return UserMapper.toDTO(userRepository.save(user))
    }

    fun updateUser(id: String, dto: UserDTO): UserDTO? {
        val existing = userRepository.findById(id).orElse(null) ?: return null
        val updated = existing.copy(
            username = dto.username,
            email = dto.email
        )
        return UserMapper.toDTO(userRepository.save(updated))
    }


    fun deleteUser(id: String): Boolean {
        return if (userRepository.existsById(id)) {
            userRepository.deleteById(id)
            true
        } else false
    }

    fun getUserByFirebaseUid(firebaseUid: String): UserDTO? {

        // usuario obtenido por firebaseUid
        return userRepository.findByFirebaseUid(firebaseUid)?.let(UserMapper::toDTO)
    }

    fun deleteUserByFirebaseUid(firebaseUid: String): Boolean {
        val user = userRepository.findByFirebaseUid(firebaseUid) ?: return false
        userRepository.delete(user)
        return true
    }
}


