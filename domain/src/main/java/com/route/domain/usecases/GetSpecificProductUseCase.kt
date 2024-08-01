package com.route.domain.usecases

import com.route.domain.common.Resource
import com.route.domain.contracts.products.ProductsRepository
import com.route.domain.models.Products
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSpecificProductUseCase @Inject constructor(
    private val productRepo : ProductsRepository
) {
    suspend fun getSpecificProduct(productId : String) : Flow<Resource<Products?>?> {
        return productRepo.getSpecificProduct(productId)
    }
}