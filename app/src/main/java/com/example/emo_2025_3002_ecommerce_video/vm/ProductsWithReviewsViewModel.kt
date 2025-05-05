package com.example.emo_2025_3002_ecommerce_video.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emo_2025_3002_ecommerce_video.DataApi
import com.example.emo_2025_3002_ecommerce_video.models.ProductsWithAvgRatingsState

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsWithReviewsViewModel @Inject constructor(private val productService: DataApi) :
    ViewModel() {


    private val _productsState = MutableStateFlow(ProductsWithAvgRatingsState())
    val productsState = _productsState.asStateFlow()
    private var productId = 0

    init {
        Log.d("juhanikikkailee", "ProductsWithReviewsViewModel::init")
        getProductsWithReviews()
    }

    fun setProductId(id: Int) {

        productId = id
        Log.d("juhanikikkailee", "ProductsWithReviewsViewModel::setProductId $productId")
    }

    fun getProductId(): Int {
        Log.d("juhanikikkailee", "ProductsWithReviewsViewModel::getProductId $productId")
        return productId
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("juhanikikkailee", "ProductsWithReviewsViewModel::onCleared")



    }


    private fun getProductsWithReviews() {
        //productService.getProductsWithReviews()
        viewModelScope.launch {
            try {
                _productsState.update { currentState ->
                    currentState.copy(loading = true, error = null)
                }
                val productReviews = productService.getProductsWithReviews()
                _productsState.update { currentState ->
                    currentState.copy(productsWithRatings = productReviews)
                }
            } catch (e: Exception) {
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