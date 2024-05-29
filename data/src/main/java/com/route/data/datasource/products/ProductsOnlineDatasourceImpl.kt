package com.route.data.datasource.products

import com.route.data.api.WebServices
import com.route.data.contracts.products.ProductOnlineDataSource
import com.route.domain.contracts.products.SortedBy
import com.route.domain.models.Products
import javax.inject.Inject

class ProductsOnlineDatasourceImpl @Inject constructor(val webServices: WebServices)
    : ProductOnlineDataSource{
    override suspend fun getProducts(
        sortedBy: SortedBy?,
        categoryId: String?,
        brandId: String?,
    ): List<Products>? {
        TODO("Not yet implemented")
    }
}