package com.route.data.repository.auth

import com.route.data.contracts.auth.AuthenticationOnlineDataSource
import com.route.data.toFlow
import com.route.domain.common.Resource
import com.route.domain.contracts.auth.AuthenticationRepository
import com.route.domain.models.AuthenticationResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val authDataSource : AuthenticationOnlineDataSource
): AuthenticationRepository {
    override suspend fun signIn(email: String, password: String)
    : Flow<Resource<AuthenticationResponse>> {
        return toFlow{ authDataSource.signIn(email, password) }
    }
}