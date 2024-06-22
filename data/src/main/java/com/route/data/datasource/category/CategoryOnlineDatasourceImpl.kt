package com.route.data.datasource.category

import com.route.data.api.webservice.CategoryWebServices
import com.route.data.contracts.category.CategoryOnlineDatasource
import com.route.data.executeAPI
import com.route.domain.models.Category
import javax.inject.Inject

class CategoryOnlineDatasourceImpl @Inject constructor(
    private val categoryWebServices: CategoryWebServices
) : CategoryOnlineDatasource {
    override suspend fun getAllCategories(): List<Category>? {
        return executeAPI {
            categoryWebServices.getCategories().data?.filterNotNull()?.map {
                it.toCategory()
            }
        }
    }
}