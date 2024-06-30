package com.route.domain.contracts.auth

import com.route.domain.common.Resource
import com.route.domain.models.AuthenticationResponse
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    suspend fun signIn(email:String,password:String) : Flow<Resource<AuthenticationResponse>>
}