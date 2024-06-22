package com.route.data.api.webservice

import com.route.data.api.model.Response
import com.route.data.api.model.SubcategoryDTO
import retrofit2.http.GET

interface SubcategoryWebService {
    @GET("/api/v1/subcategories")
    suspend fun getSubCategory() : Response<List<SubcategoryDTO?>?>
}