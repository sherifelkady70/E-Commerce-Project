package com.route.domain.usecases.auth

import com.route.domain.common.Resource
import com.route.domain.contracts.auth.AuthenticationRepository
import com.route.domain.models.AuthenticationResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository : AuthenticationRepository
) {
    suspend fun login(email:String,password:String):Flow<Resource<AuthenticationResponse>>{
        return authRepository.signIn(email,password)
    }
}