package com.route.data.datasource.products

import com.route.data.contracts.products.ProductOnlineDataSource
import com.route.domain.contracts.products.SortedBy
import com.route.domain.models.Products

class ProductsOnlineDatasourceImpl : ProductOnlineDataSource{
    override suspend fun getProducts(
        sortedBy: SortedBy?,
        categoryId: String?,
        brandId: String?,
    ): List<Products>? {
        TODO("Not yet implemented")
    }
}