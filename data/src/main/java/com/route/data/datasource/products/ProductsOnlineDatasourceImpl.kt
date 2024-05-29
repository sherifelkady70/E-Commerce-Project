package com.route.data.datasource.products

import com.route.data.api.WebServices
import com.route.data.contracts.products.ProductOnlineDataSource
import com.route.data.executeAPI
import com.route.domain.contracts.products.SortedBy
import com.route.domain.models.Products
import javax.inject.Inject

class ProductsOnlineDatasourceImpl @Inject constructor(private val webServices: WebServices)
    : ProductOnlineDataSource{
    override suspend fun getProducts(
        sortedBy: SortedBy?,
        categoryId: String?,
        brandId: String?,
    ): List<Products>? {
        return executeAPI {
            webServices.getProducts(
                sortedBy?.value,brandId,categoryId
            )
        }.data?.map {
            it!!.toProducts()
        }
    }
}