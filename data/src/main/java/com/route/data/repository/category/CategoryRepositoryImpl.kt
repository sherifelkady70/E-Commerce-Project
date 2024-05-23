package com.route.data.repository.category

import com.route.data.contracts.category.CategoryOnlineDatasource
import com.route.data.toFlow
import com.route.domain.common.Resource
import com.route.domain.contracts.category.CategoriesRepository
import com.route.domain.models.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryOnlineDatasource: CategoryOnlineDatasource
) : CategoriesRepository {
    override suspend fun getAllCategories(): Flow<Resource<List<Category>?>> {
        return toFlow {
             categoryOnlineDatasource.getAllCategories()
        }
    }
}