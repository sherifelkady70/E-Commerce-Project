package com.route.domain.contracts.products

import com.route.domain.common.Resource
import com.route.domain.models.Products
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProducts(
        categoryId : String?=null,
        sortedBy : SortedBy?=null,
        brandId : String?=null
    ) : Flow<Resource<List<Products>?>>
    suspend fun getSpecificProduct(productId : String) : Flow<Resource<Products?>?>
}
enum class SortedBy (val value : String){
    PRICE_DESC("-price"),
    PRICE_ASC("price"),
    MOST_SELLING("-sold")
}