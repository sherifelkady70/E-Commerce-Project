package com.route.data.api

import com.route.data.api.model.CategoryDTO
import com.route.data.api.model.ProductsDTO
import com.route.data.api.model.Response
import com.route.domain.contracts.products.SortedBy
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {
    @GET("/api/v1/categories")
    suspend fun getCategories() : Response<List<CategoryDTO?>?>

    @GET("/api/v1/products")
    suspend fun getProducts(
        @Query("sort") sortedBy:String?=null,
        @Query("brand") brand:String?=null,
        @Query("category") category:String?=null
    ) : Response<List<ProductsDTO?>?>
}