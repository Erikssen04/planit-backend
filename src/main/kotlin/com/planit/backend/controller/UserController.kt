package com.planit.backend.controller

import com.planit.backend.dto.UserDTO
import com.planit.backend.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import org.springframework.http.HttpStatus

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService
) {

    @GetMapping
    fun getAllUsers(): List<UserDTO> =
        userService.getAllUsers()

    @PostMapping
    fun createUser(@RequestBody userDTO: UserDTO, @RequestHeader("Authorization") token: String): UserDTO {
        val userId = SecurityContextHolder.getContext().authentication.principal as String
        return userService.createUser(userDTO.copy(firebaseUid = userId))
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: String, @RequestBody userDTO: UserDTO): UserDTO? =
        userService.updateUser(id, userDTO)

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: String): Boolean =
        userService.deleteUser(id)

    @DeleteMapping("/me")
    fun deleteCurrentUser(@RequestHeader("Authorization") token: String): ResponseEntity<String> {
        val firebaseUid = SecurityContextHolder.getContext().authentication.principal as String
        val deleted = userService.deleteUserByFirebaseUid(firebaseUid)
        return if (deleted) {
            ResponseEntity.ok("Usuario eliminado correctamente")
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado")
        }
    }
}
