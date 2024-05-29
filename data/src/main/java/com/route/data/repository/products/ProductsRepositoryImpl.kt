package com.route.data.repository.products

import com.route.data.contracts.products.ProductOnlineDataSource
import com.route.domain.common.Resource
import com.route.domain.contracts.products.ProductsRepository
import com.route.domain.contracts.products.SortedBy
import com.route.domain.models.Products
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    val productsOnlineDatasource : ProductOnlineDataSource)
    : ProductsRepository {
    override suspend fun getProducts(
        categoryId: String?,
        sortedBy: SortedBy?,
        brandId: String?,
    ): Flow<Resource<List<Products>?>> {
        TODO("Not yet implemented")
    }
}