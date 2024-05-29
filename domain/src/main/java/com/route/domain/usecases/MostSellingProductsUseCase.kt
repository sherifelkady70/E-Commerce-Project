package com.route.domain.usecases

import com.route.domain.common.Resource
import com.route.domain.contracts.products.ProductsRepository
import com.route.domain.contracts.products.SortedBy
import com.route.domain.models.Products
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MostSellingProductsUseCase @Inject constructor(
    private val productsRepository : ProductsRepository
) {
    suspend fun invoke() : Flow<Resource<List<Products>?>>{
        return productsRepository.getProducts(
            sortedBy = SortedBy.MOST_SELLING
        )
    }
}