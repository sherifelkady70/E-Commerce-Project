package com.route.domain.usecases

import com.route.domain.contracts.products.ProductsRepository
import javax.inject.Inject

class ProductsUseCase @Inject constructor(
    val ProductsRepository : ProductsRepository
) {

}