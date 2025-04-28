package com.example.emo_2025_3002_ecommerce_video

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

val retrofit = Retrofit.Builder()
    .baseUrl("http://10.0.2.2:7006/api/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface  DataApi {
    // http://10.0.2.2/api/jotakin
    @GET("product_reviews/")
    suspend fun getProductsWithReviews()
}

val productService = retrofit.create(DataApi::class.java)