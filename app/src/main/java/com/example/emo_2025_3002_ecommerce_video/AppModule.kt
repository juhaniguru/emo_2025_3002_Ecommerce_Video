package com.example.emo_2025_3002_ecommerce_video

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataApiModule {

    @Provides
    @Singleton
    fun provideDataApi() : DataApi {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:7006/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(DataApi::class.java)
    }
}