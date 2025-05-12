package com.example.emo_2025_3002_ecommerce_video

import androidx.lifecycle.SavedStateHandle
import com.example.emo_2025_3002_ecommerce_video.models.ProductDto
import com.example.emo_2025_3002_ecommerce_video.models.ProductWithAvgRatingDto
import com.example.emo_2025_3002_ecommerce_video.vm.ProductsWithReviewsViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class MockProductServiceOK : DataApi {
    override suspend fun getProductsWithReviews(): List<ProductWithAvgRatingDto> {
        return listOf(
            ProductWithAvgRatingDto(
                id = 1,
                productName = "tuote",
                rating = 0f,
                reviewCount = 0,
                categoryName = "Kategoria"
            )
        )
    }

    override suspend fun getProduct(productId: Int): ProductDto? {
        TODO("Not yet implemented")
    }

    override suspend fun removeReview(productId: Int, reviewId: Int) {
        TODO("Not yet implemented")
    }
}

class ProductsWithReviewsViewModelTest {
    private lateinit var vm: ProductsWithReviewsViewModel


    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        val productService = MockProductServiceOK()
        val savedStateHandle = SavedStateHandle()
        vm = ProductsWithReviewsViewModel(productService,savedStateHandle)
    }

    @Test
    fun getProductsWithAvgRatingsOK() {
        val expectedData = listOf(
            ProductWithAvgRatingDto(
                id = 1,
                productName = "tuote",
                rating = 0f,
                reviewCount = 0,
                categoryName = "Kategoria"
            )
        )

        assertEquals(expectedData, vm.productsState.value.productsWithRatings)
    }



    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}