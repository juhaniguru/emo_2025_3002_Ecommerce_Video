package com.example.emo_2025_3002_ecommerce_video.vm

import androidx.lifecycle.ViewModel
import com.example.emo_2025_3002_ecommerce_video.models.ProductsWithAvgRatingsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ProductsWithReviewsViewModel @Inject constructor(): ViewModel() {
    private val _productsState = MutableStateFlow(ProductsWithAvgRatingsState())

}