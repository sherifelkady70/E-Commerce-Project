package com.route.data.contracts.auth

import com.route.data.api.model.auth.AuthenticationResponse

interface AuthenticationOnlineDataSource {
    suspend fun signIn(
        email:String,
        password:String
    ) : AuthenticationResponse
}