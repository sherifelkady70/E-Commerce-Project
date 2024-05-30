package com.route.domain.usecases

import com.route.domain.common.Resource
import com.route.domain.contracts.categoryproducts.CategoryProductsRepository
import com.route.domain.models.Products
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryProductsUseCase @Inject constructor(
    private val categoryProductsRepository: CategoryProductsRepository
){
    suspend fun getCategoryProducts() : Flow<Resource<List<Products>?>> {
        return categoryProductsRepository.getCategoryProducts()
    }
}