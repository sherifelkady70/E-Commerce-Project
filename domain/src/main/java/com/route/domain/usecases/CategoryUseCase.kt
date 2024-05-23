package com.route.domain.usecases

import com.route.domain.common.Resource
import com.route.domain.contracts.category.CategoriesRepository
import com.route.domain.models.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CategoryUseCase @Inject constructor(
    private val categoriesRepository: CategoriesRepository
){

    suspend fun invoke() : Flow<Resource<List<Category>?>> {
        return categoriesRepository.getAllCategories()
    }
}