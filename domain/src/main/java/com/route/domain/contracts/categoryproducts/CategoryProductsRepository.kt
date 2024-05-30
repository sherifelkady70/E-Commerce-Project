package com.route.domain.contracts.categoryproducts

import com.route.domain.common.Resource
import com.route.domain.models.Products
import kotlinx.coroutines.flow.Flow

interface CategoryProductsRepository {

    suspend fun getCategoryProducts() :Flow<Resource<List<Products>?>>
}