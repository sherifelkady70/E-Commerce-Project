package com.route.data.repository

import com.route.data.repository.category.CategoryRepositoryImpl
import com.route.domain.contracts.category.CategoriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideCategoryRepository(impl : CategoryRepositoryImpl)
    : CategoriesRepository
}