package com.route.data.contracts.subcategory

import com.route.domain.models.Subcategory

interface SubCategoryDataSource {
    suspend fun getSubCategory():List<Subcategory>?
}