package com.route.data.datasource

import com.route.data.contracts.category.CategoryOnlineDatasource
import com.route.data.datasource.category.CategoryOnlineDatasourceImpl
import com.route.data.repository.products.ProductsRepositoryImpl
import com.route.domain.contracts.products.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class OnlineDatasourceModule {

    @Binds
    abstract fun provideCategoryOnlineDatasource(impl : CategoryOnlineDatasourceImpl)
    : CategoryOnlineDatasource

    @Binds
    abstract fun provideProductsOnlineDatasource(impl : ProductsRepositoryImpl)
    : ProductsRepository
}