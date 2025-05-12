package com.example.emo_2025_3002_ecommerce_video

import com.example.emo_2025_3002_ecommerce_video.models.ProductDto
import com.example.emo_2025_3002_ecommerce_video.models.ProductWithAvgRatingDto

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path


interface  DataApi {
    // http://10.0.2.2/api/product_reviews/
    @GET("product_reviews/")
    suspend fun getProductsWithReviews() : List<ProductWithAvgRatingDto>

    @GET("products/{productId}")
    suspend fun getProduct(@Path("productId") productId: Int): ProductDto?

    @DELETE("product_reviews/{productId}/reviews/{reviewId}")
    suspend fun removeReview(@Path("productId") productId: Int, @Path("reviewId") reviewId: Int)
}

