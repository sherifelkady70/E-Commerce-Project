package com.example.routee_commerce.utils

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DI {


    @Provides
    fun provideSharedPreferences(@ApplicationContext context : Context) : SharedPreferences{
        return context.getSharedPreferences("My SP",Context.MODE_PRIVATE)
    }
}