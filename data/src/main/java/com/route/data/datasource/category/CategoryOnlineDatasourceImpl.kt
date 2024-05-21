package com.route.data.datasource.category

import com.route.data.api.WebServices
import com.route.data.contracts.category.CategoryOnlineDatasource
import com.route.domain.models.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryOnlineDatasourceImpl @Inject constructor(
    private val webServices: WebServices
) : CategoryOnlineDatasource {
    override suspend fun getAllCategories(): Flow<List<Category>?> {
        return webServices.getCategories().map { it ->
            it.data?.filterNotNull()?.map {
                it.toCategory()
            }
        }
    }
}