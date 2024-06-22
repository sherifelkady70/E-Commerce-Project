package com.route.data.repository.subcategory

import com.route.data.contracts.subcategory.SubCategoryDataSource
import com.route.data.toFlow
import com.route.domain.common.Resource
import com.route.domain.contracts.subcategory.SubCategoryRepository
import com.route.domain.models.Subcategory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubCategoryRepositoryImpl @Inject constructor(
    private val subCategoryDatasource : SubCategoryDataSource
): SubCategoryRepository {
    override suspend fun getSubCategory(): Flow<Resource<List<Subcategory>?>> {
        return toFlow{ subCategoryDatasource.getSubCategory() }
    }
}