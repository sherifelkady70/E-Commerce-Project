package com.route.domain.usecases

import com.route.domain.common.Resource
import com.route.domain.contracts.subcategory.SubCategoryRepository
import com.route.domain.models.Subcategory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubCategoryUseCase @Inject constructor(
    private val subCategoryRepository : SubCategoryRepository
){
    suspend fun getSubCategory() : Flow<Resource<List<Subcategory>?>> {
        return subCategoryRepository.getSubCategory()
    }
}