package com.example.emo_2025_3002_ecommerce_video.models

import com.google.gson.annotations.SerializedName

data class ProductWithAvgRatingDto(
    val id: Int,
    @SerializedName("product_name")
    val productName: String,
    val rating: Float?,
    @SerializedName("review_count")
    val reviewCount: Int,
    @SerializedName("category_name")
    val categoryName: String,

)

data class ProductsWithAvgRatingsState(
    val error: String? = null,
    val loading: Boolean = false,
    val productsWithRatings: List<ProductWithAvgRatingDto> = emptyList()
)

data class RatingDto(
    val id: Int,
    val rating: Float,
    @SerializedName("date_reviewed")
    val dateReviewed: String,
    val message: String?
)

data class ProductDto(val name: String, val review: List<RatingDto>)

data class RatingsState(
    val error: String? = null,
    val loading: Boolean = false,
    val product: ProductDto? = null
)

