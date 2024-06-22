package com.route.data.contracts.subcategory

import com.route.data.api.model.SubcategoryDTO

interface SubCategoryDataSource {
    suspend fun getSubCategory():List<SubcategoryDTO>?
}