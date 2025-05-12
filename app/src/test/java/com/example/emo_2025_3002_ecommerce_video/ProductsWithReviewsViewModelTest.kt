package com.example.emo_2025_3002_ecommerce_video

import androidx.lifecycle.SavedStateHandle
import com.example.emo_2025_3002_ecommerce_video.models.ProductDto
import com.example.emo_2025_3002_ecommerce_video.models.ProductWithAvgRatingDto
import com.example.emo_2025_3002_ecommerce_video.vm.ProductsWithReviewsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.DefaultAsserter.assertEquals
import kotlin.test.assertEquals

class MockProductsService : DataApi {
    override suspend fun getProductsWithReviews(): List<ProductWithAvgRatingDto> {
        return listOf(
            ProductWithAvgRatingDto(id=1, productName = "tuote", rating = 0f, reviewCount = 0, categoryName = "kategoria")
        )
    }

    override suspend fun getProduct(productId: Int): ProductDto? {
        TODO("Not yet implemented")
    }

}

class ProductsWithReviewsViewModelTest {
    private lateinit var vm: ProductsWithReviewsViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        // Tällä viewmodelissa käytettävä coroutineScope (ViewModelScope)
        // saadaan suoritettua TestThreadissa
        // testit on hyvä suorittaa emulaattorin ulkopuolella (Ei siis mainthreadissa)
        Dispatchers.setMain(Dispatchers.Unconfined)
        // tehdään isntanssi mockupservicesta testiä varten

        val productsService = MockProductsService()
        val savedStateHandle = SavedStateHandle()

        vm = ProductsWithReviewsViewModel(productsService, savedStateHandle)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        // tämä rivi palauttaa mainthreadin ennalleen testien jälkeen
        Dispatchers.resetMain()
    }

    @Test
    fun testGetReviews() {
        val expected = listOf(
            ProductWithAvgRatingDto(id=1, productName = "tuote", rating = 0f, reviewCount = 0, categoryName = "kategoria")
        )

        assertEquals(expected, vm.productsState.value.productsWithRatings)
    }

}