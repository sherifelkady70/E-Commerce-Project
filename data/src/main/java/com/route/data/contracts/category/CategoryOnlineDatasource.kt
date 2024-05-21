package com.route.data.contracts.category

import com.route.domain.models.Category
import kotlinx.coroutines.flow.Flow

interface CategoryOnlineDatasource {
    suspend fun getAllCategories() : Flow<List<Category>?>
}