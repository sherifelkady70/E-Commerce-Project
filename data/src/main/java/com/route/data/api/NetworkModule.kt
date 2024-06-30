package com.route.data.api

import android.util.Log
import com.route.data.api.webservice.CategoryWebServices
import com.route.data.api.webservice.SubcategoryWebService
import com.route.data.api.webservice.auth.AuthenticationWebService
import com.route.data.api.webservice.wishlist.WishlistWebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    fun provideLoggingInterceptor() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor{
            Log.e("API",it)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    @Provides
    fun provideOkHttpClint(loggingInterceptor: HttpLoggingInterceptor) : OkHttpClient {
        return OkHttpClient
            .Builder()
            .addNetworkInterceptor(loggingInterceptor)
            .build()
    }
    @Provides
    fun provideGsonConverterFactory():GsonConverterFactory{
        return GsonConverterFactory.create()
    }
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient ,
        gsonConverterFactory: GsonConverterFactory
    ) : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .baseUrl("https://ecommerce.routemisr.com/")
            .build()
    }

    @Provides
    fun provideCategoryWebServices(retrofit: Retrofit) : CategoryWebServices {
        return retrofit.create(CategoryWebServices::class.java)
    }

    @Provides
    fun provideSubCategoryWebService(retrofit: Retrofit) : SubcategoryWebService {
        return retrofit.create(SubcategoryWebService::class.java)
    }

    @Provides
    fun provideWishlistWebService(retrofit: Retrofit) : WishlistWebService {
        return retrofit.create(WishlistWebService::class.java)
    }
    @Provides
    fun provideAuthWebService(retrofit: Retrofit) : AuthenticationWebService {
        return retrofit.create(AuthenticationWebService::class.java)
    }
}