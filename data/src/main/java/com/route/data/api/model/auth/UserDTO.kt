package com.route.data.api.model.auth

import com.route.domain.models.User

data class UserDTO(
    val role: String? = null,
    val name: String? = null,
    val email: String? = null,
){
    fun toUser(): User {
        return User(role,name,email)
    }
}
