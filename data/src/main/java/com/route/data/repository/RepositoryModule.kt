package com.route.data.repository

import com.route.data.repository.category.CategoryRepositoryImpl
import com.route.data.repository.categoryProducts.CategoryProductsRepositoryImpl
import com.route.data.repository.products.ProductsRepositoryImpl
import com.route.data.repository.subcategory.SubCategoryRepositoryImpl
import com.route.domain.contracts.category.CategoriesRepository
import com.route.domain.contracts.categoryproducts.CategoryProductsRepository
import com.route.domain.contracts.products.ProductsRepository
import com.route.domain.contracts.subcategory.SubCategoryRepository
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

    @Binds
    abstract fun provideProductsRepository(impl : ProductsRepositoryImpl)
    : ProductsRepository

    @Binds
    abstract fun provideCategoryProductsRepository(impl : CategoryProductsRepositoryImpl)
    : CategoryProductsRepository

    @Binds
    abstract fun provideSubCategoryRepository(impl : SubCategoryRepositoryImpl)
    : SubCategoryRepository
}