package com.route.data.datasource

import com.route.data.contracts.category.CategoryOnlineDatasource
import com.route.data.contracts.categoryproducts.CategoryProductsOnlineDatasource
import com.route.data.contracts.products.ProductOnlineDataSource
import com.route.data.contracts.subcategory.SubCategoryDataSource
import com.route.data.contracts.wishlist.WishlistDataSource
import com.route.data.datasource.category.CategoryOnlineDatasourceImpl
import com.route.data.datasource.categoryproducts.CategoryProductsOnlineDatasourceImpl
import com.route.data.datasource.products.ProductsOnlineDatasourceImpl
import com.route.data.datasource.subcategory.SubCategoryDataSourceImpl
import com.route.data.datasource.wishlist.WishlistDataSourceImpl
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
    abstract fun provideProductsOnlineDatasource(impl : ProductsOnlineDatasourceImpl)
    : ProductOnlineDataSource

    @Binds
    abstract fun provideCategoryProductsDatasource(impl : CategoryProductsOnlineDatasourceImpl)
    : CategoryProductsOnlineDatasource

    @Binds
    abstract fun provideSunCategoryDatasource(impl : SubCategoryDataSourceImpl)
    : SubCategoryDataSource

    @Binds
    abstract fun provideWishlistDatasource(impl: WishlistDataSourceImpl)
    : WishlistDataSource
}