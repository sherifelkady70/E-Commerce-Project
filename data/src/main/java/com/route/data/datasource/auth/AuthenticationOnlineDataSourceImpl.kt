package com.route.data.datasource.auth

import com.route.data.api.webservice.auth.AuthenticationWebService
import com.route.data.contracts.auth.AuthenticationOnlineDataSource
import com.route.data.executeAPI
import javax.inject.Inject

class AuthenticationOnlineDataSourceImpl @Inject constructor(
    private val authWebService : AuthenticationWebService
) : AuthenticationOnlineDataSource {
    override suspend fun signIn(email: String, password: String):
            com.route.domain.models.AuthenticationResponse {
        val response =  executeAPI { authWebService.signIn(email,password) }
        val authResponse = com.route.domain.models.AuthenticationResponse(
            response.user?.toUser() , response.token
        )
        return authResponse
    }
}