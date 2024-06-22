package com.route.domain.contracts.subcategory

import com.route.domain.common.Resource
import com.route.domain.models.Subcategory
import kotlinx.coroutines.flow.Flow

interface SubCategoryRepository {
    suspend fun getSubCategory() : Flow<Resource<List<Subcategory>?>>
}