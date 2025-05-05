package com.example.emo_2025_3002_ecommerce_video

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.emo_2025_3002_ecommerce_video.vm.ProductsWithReviewsViewModel

@Composable
fun RatingsScreenRoot(
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    viewModel: ProductsWithReviewsViewModel
) {
    var productId = viewModel.getProductId()
    RatingScreen(onNavigate = onNavigate, productId = productId)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RatingScreen(modifier: Modifier = Modifier, onNavigate: () -> Unit, productId: Int) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text("Toinen sivu")
        }, navigationIcon = {
            IconButton(onClick = {
                Log.d("juhanikikkailee", "onNavigate")
                onNavigate()
            }) {
                Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Back")
            }
        })
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("toinen sivu $productId")
        }
    }
}