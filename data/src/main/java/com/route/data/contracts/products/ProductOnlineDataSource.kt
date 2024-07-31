package com.route.data.contracts.products

import com.route.domain.contracts.products.SortedBy
import com.route.domain.models.Products

interface ProductOnlineDataSource {
    suspend fun getProducts(sortedBy: SortedBy?,
                            categoryId : String?,
                            brandId : String?) : List<Products>?

    suspend fun getSpecificProduct(productId : String) : Products?
}