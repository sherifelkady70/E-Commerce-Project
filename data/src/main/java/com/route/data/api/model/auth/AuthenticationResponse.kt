package com.route.data.api.model.auth

data class AuthenticationResponse(
	val message: String? = null,
	val statusMessage : String? =null,
	val user: UserDTO? = null,
	val token: String? = null
)


