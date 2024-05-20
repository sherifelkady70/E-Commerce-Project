package com.route.data.api

import android.util.Log
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
    fun provideWebServices(retrofit: Retrofit) : WebServices {
        return retrofit.create(WebServices::class.java)
    }
}