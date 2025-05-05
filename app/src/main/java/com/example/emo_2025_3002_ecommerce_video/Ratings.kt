package com.example.emo_2025_3002_ecommerce_video

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.emo_2025_3002_ecommerce_video.models.ProductsWithAvgRatingsState
import com.example.emo_2025_3002_ecommerce_video.models.RatingDto
import com.example.emo_2025_3002_ecommerce_video.models.RatingsState
import com.example.emo_2025_3002_ecommerce_video.vm.ProductsWithReviewsViewModel

@Composable
fun RatingsScreenRoot(modifier: Modifier = Modifier, viewModel: ProductsWithReviewsViewModel) {
    val state by viewModel.ratingsByProductState.collectAsStateWithLifecycle()
    RatingsScreen(state = state)

}

@Composable
@OptIn(ExperimentalMaterial3Api::class)

fun RatingsScreen(
    modifier: Modifier = Modifier,
    state: RatingsState
) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text("Reviews")
        }, navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Default.Menu, contentDescription = "Open Menu")
            }
        })
    }) { paddingValues ->
        when {
            state.loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            else -> {
                state.error?.let { err ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(err)
                    }
                } ?: LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    items(state.product?.review ?: emptyList(), key = { p ->
                        p.id
                    }) { rating ->
                        RatingItem(item = rating)
                    }
                }
            }
        }
    }
}

@Composable
fun RatingItem(modifier: Modifier = Modifier, item: RatingDto) {

}