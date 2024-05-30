package com.route.data.repository.categoryProducts

import com.route.data.contracts.categoryproducts.CategoryProductsOnlineDatasource
import com.route.data.toFlow
import com.route.domain.common.Resource
import com.route.domain.contracts.categoryproducts.CategoryProductsRepository
import com.route.domain.models.Products
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

 class CategoryProductsRepositoryImpl  @Inject constructor(
    private val categoryProductsOnlineDatasource: CategoryProductsOnlineDatasource
): CategoryProductsRepository
{
    override suspend fun getCategoryProducts(): Flow<Resource<List<Products>?>> {
        return toFlow{ categoryProductsOnlineDatasource.getCategoryProducts() }
    }
}