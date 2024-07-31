package com.route.data.api.webservice

import retrofit2.http.POST

interface CartWebService {

    @POST("api/v1/cart")
    suspend fun addProductToCard() :
}