package com.example.emo_2025_3002_ecommerce_video

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.emo_2025_3002_ecommerce_video.models.ProductWithAvgRatingDto
import com.example.emo_2025_3002_ecommerce_video.models.ProductsWithAvgRatingsState
import com.example.emo_2025_3002_ecommerce_video.ui.theme.Emo_2025_3002_Ecommerce_VideoTheme
import com.example.emo_2025_3002_ecommerce_video.vm.ProductsWithReviewsViewModel

@Composable
fun ProductsWithAvgRatingsRoot(
    modifier: Modifier = Modifier,
    viewmodel: ProductsWithReviewsViewModel,
    onNavigate: () -> Unit
) {


    val state by viewmodel.productsState.collectAsStateWithLifecycle()
    ProductsWithAvgRatingsScreen(state = state, onNavigate = { chosenProductId ->
        viewmodel.setProductId(chosenProductId)
        onNavigate()

    })


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsWithAvgRatingsScreen(
    modifier: Modifier = Modifier,
    state: ProductsWithAvgRatingsState,
    onNavigate: (Int) -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text("Products & Reviews")
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
                    items(state.productsWithRatings, key = { p ->
                        p.id
                    }) { productWithAvgRating ->
                        ProductWithAvgRatingItem(
                            item = productWithAvgRating,
                            onNavigate = onNavigate
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProductWithAvgRatingItem(
    modifier: Modifier = Modifier,
    item: ProductWithAvgRatingDto,
    onNavigate: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onNavigate(item.id)
            }
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            AsyncImage(
                model = R.drawable.review,
                contentDescription = "Product list placeholder image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {
                Text(
                    item.productName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                RatingBar(rating = item.rating ?: 0f, reviewCount = item.reviewCount)
                Text(item.categoryName)

            }
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun ProductsWithAvgRatingsScreenPreview() {
    Emo_2025_3002_Ecommerce_VideoTheme {
        val state =
            ProductsWithAvgRatingsState(

                productsWithRatings = listOf(
                    ProductWithAvgRatingDto(
                        id = 1,
                        productName = "Hyvä tuotesfdlkjsfdklsfdsfkljsflkljsfkld sfdkljsfdklsfd sfdkljsfdklsfd sfdlkjsfdklsfdj ",
                        rating = 5f,
                        reviewCount = 1000,
                        categoryName = "Kova kategoria"
                    ),
                    ProductWithAvgRatingDto(
                        id = 2,
                        productName = "Hyvä tuote 2",
                        rating = 4.3f,
                        reviewCount = 100,
                        categoryName = "Kova kategoria"
                    ),
                    ProductWithAvgRatingDto(
                        id = 3,
                        productName = "Aivan OK tuote",
                        rating = 3.7f,
                        reviewCount = 100,
                        categoryName = "Kova kategoria"
                    )

                )
            )
        ProductsWithAvgRatingsScreen(state = state, onNavigate = {})
    }
}