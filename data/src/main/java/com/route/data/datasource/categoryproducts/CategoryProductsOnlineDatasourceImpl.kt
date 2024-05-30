package com.route.data.datasource.categoryproducts

import com.route.data.api.WebServices
import com.route.data.contracts.categoryproducts.CategoryProductsOnlineDatasource
import com.route.data.executeAPI
import com.route.domain.models.Products
import javax.inject.Inject

class CategoryProductsOnlineDatasourceImpl @Inject constructor(
    private val webServices: WebServices) : CategoryProductsOnlineDatasource{
    override suspend fun getCategoryProducts() : List<Products>? {
       return executeAPI {
           webServices.getCategoryProducts().data?.filterNotNull()?.map {
               it.toProducts()
           }
       }
    }

}