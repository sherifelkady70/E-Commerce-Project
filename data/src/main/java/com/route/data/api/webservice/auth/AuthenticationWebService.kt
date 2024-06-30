package com.route.data.api.webservice.auth

import com.route.data.api.model.auth.AuthenticationResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthenticationWebService {
    @FormUrlEncoded
    @POST("/api/v1/auth/signin")
    suspend fun signIn(
        @Field("email") email : String,
        @Field("password") password : String
    ) : AuthenticationResponse
}