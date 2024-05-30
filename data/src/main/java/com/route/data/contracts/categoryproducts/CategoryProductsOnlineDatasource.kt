package com.route.data.contracts.categoryproducts

import com.route.domain.common.Resource
import com.route.domain.models.Products

interface CategoryProductsOnlineDatasource {
    suspend fun getCategoryProducts() : List<Products>?
}