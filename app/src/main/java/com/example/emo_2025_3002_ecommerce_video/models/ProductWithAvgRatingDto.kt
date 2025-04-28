package com.example.emo_2025_3002_ecommerce_video.models

data class ProductWithAvgRatingDto(
    val id: Int,

    val productName: String,
    val rating: Float?,
    val reviewCount: Int,
    val categoryName: String
)

data class ProductsWithAvgRatingsState(
    val error: String? = null,
    val loading: Boolean = false,
    val productsWithRatings: List<ProductWithAvgRatingDto> = emptyList()
)
