package com.example.emo_2025_3002_ecommerce_video.vm

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emo_2025_3002_ecommerce_video.DataApi
import com.example.emo_2025_3002_ecommerce_video.models.ProductDto
import com.example.emo_2025_3002_ecommerce_video.models.ProductsWithAvgRatingsState
import com.example.emo_2025_3002_ecommerce_video.models.RatingsState

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsWithReviewsViewModel @Inject constructor(
    private val productService: DataApi,
    private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {


    private val _productsState = MutableStateFlow(ProductsWithAvgRatingsState())
    val productsState = _productsState.asStateFlow()

    private val _ratingsByProductState = MutableStateFlow(RatingsState())
    val ratingsByProductState = _ratingsByProductState.asStateFlow()

    init {
        Log.d("juhanikikkailee_video", "ProductsWithReviewsViewModel::init")
        getProductsWithReviews()
    }

    fun poistaArvostelu(ratingId: Int) {
        viewModelScope.launch {
            val reviews = _ratingsByProductState.value.product?.review ?: emptyList()
            val remainingReviews = reviews.filter { rating ->
                rating.id != ratingId
            }
            val product = _ratingsByProductState.value.product
            val newProduct = ProductDto(name = product?.name ?: "", review = remainingReviews)
            _ratingsByProductState.update { currentState ->
                currentState.copy(product = newProduct)
            }
        }




    }

    fun haeArvostelutTuotteelle() {
        viewModelScope.launch {
            try {
                _ratingsByProductState.update { currentState ->
                    currentState.copy(loading = true, error = null)
                }

                savedStateHandle.get<Int>("productId")?.let{ pId ->
                    val product = productService.getProduct(pId)
                    _ratingsByProductState.update { currentState ->
                        currentState.copy(product = product)
                    }

                }

            } catch(e: Exception) {
                _ratingsByProductState.update { currentState ->
                    currentState.copy(error = e.toString())
                }
            } finally {
                _ratingsByProductState.update { currentState ->
                    currentState.copy(loading = false)
                }
            }
        }
    }

    fun setProductId(id: Int) {
        savedStateHandle["productId"] = id
    }

    /*fun getProductId() {
        savedStateHandle.get<Int>("productId")
    }*/


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