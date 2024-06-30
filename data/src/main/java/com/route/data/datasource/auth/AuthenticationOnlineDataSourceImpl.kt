package com.route.data.datasource.auth

import com.route.data.api.model.auth.AuthenticationResponse
import com.route.data.api.webservice.auth.AuthenticationWebService
import com.route.data.contracts.auth.AuthenticationOnlineDataSource
import com.route.data.executeAPI
import javax.inject.Inject

class AuthenticationOnlineDataSourceImpl @Inject constructor(
    private val authWebService : AuthenticationWebService
) : AuthenticationOnlineDataSource {
    override suspend fun signIn(email: String, password: String): AuthenticationResponse {
        return executeAPI { authWebService.signIn(email,password) }
    }
}