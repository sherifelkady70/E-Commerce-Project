package com.route.domain.usecases

import com.route.domain.common.Resource
import com.route.domain.contracts.products.ProductsRepository
import com.route.domain.models.Products
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsUseCase @Inject constructor(
    private val ProductsRepository : ProductsRepository
) {
    suspend fun invoke() : Flow<Resource<List<Products>?>>{
        return ProductsRepository.getProducts()
    }
}