package com.route.data.datasource.subcategory

import com.route.data.api.model.SubcategoryDTO
import com.route.data.api.webservice.SubcategoryWebService
import com.route.data.contracts.subcategory.SubCategoryDataSource
import com.route.data.executeAPI
import com.route.domain.models.Subcategory
import javax.inject.Inject

class SubCategoryDataSourceImpl @Inject constructor(
    private val subCategoryWebService : SubcategoryWebService
): SubCategoryDataSource{
    override suspend fun getSubCategory(): List<Subcategory>? {
        return executeAPI {
            subCategoryWebService.getSubCategory().data?.filterNotNull()?.map {
                it.toSubCategory()
            } ?: listOf()
        }
    }

}