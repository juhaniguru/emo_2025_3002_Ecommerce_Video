package com.example.emo_2025_3002_ecommerce_video

import com.example.emo_2025_3002_ecommerce_video.models.ProductWithAvgRatingDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET



interface  DataApi {
    // http://10.0.2.2/api/product_reviews/
    @GET("product_reviews/")
    suspend fun getProductsWithReviews() : List<ProductWithAvgRatingDto>
}

