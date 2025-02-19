package com.route.domain.contracts.category

import com.route.domain.common.Resource
import com.route.domain.models.Category
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
    suspend fun getAllCategories() : Flow<Resource<List<Category>?>>
}