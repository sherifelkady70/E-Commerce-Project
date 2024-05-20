package com.route.data.api

import com.route.domain.models.Category
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface WebServices {
    @GET("/api/v1/categories")
    suspend fun getAllCategories() : Flow<List<Category>>
}