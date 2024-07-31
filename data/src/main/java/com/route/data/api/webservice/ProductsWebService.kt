package com.route.data.api.webservice

import com.route.data.api.model.ProductsDTO
import com.route.data.api.model.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsWebService {
    @GET("/api/v1/products/{productId}")
    suspend fun getSpecificProduct(
        @Path("productId") productId : String
    ) : Response<ProductsDTO?>
}