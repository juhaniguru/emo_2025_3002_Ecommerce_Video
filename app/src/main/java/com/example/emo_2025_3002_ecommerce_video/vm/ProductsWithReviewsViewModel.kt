package com.example.emo_2025_3002_ecommerce_video.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emo_2025_3002_ecommerce_video.models.ProductsWithAvgRatingsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsWithReviewsViewModel @Inject constructor(): ViewModel() {
    private val _productsState = MutableStateFlow(ProductsWithAvgRatingsState())
    val productsState = _productsState.asStateFlow()

    fun getProductsWithReviews() {
        viewModelScope.launch {
            try {
                _productsState.update { currentState ->
                    currentState.copy(loading = true, error = null)
                }
                // TODO: yritetään hakea data netistä
            } catch(e: Exception) {
              _productsState.update { currentState ->
                  currentState.copy(error = e.toString())
              }
            } finally {
                _productsState.update { currentState ->
                    currentState.copy(loading = false)
                }
            }
        }
    }

}