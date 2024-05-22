package com.route.domain.usecases

import com.route.domain.contracts.category.CategoriesRepository
import com.route.domain.models.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CategoryUseCase @Inject constructor(
    private val categoriesRepository: CategoriesRepository
){

    suspend fun invoke() : Flow<List<Category>?> {
        return categoriesRepository.getAllCategories()
    }
}