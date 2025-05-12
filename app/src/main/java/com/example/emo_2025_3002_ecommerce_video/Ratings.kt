package com.example.emo_2025_3002_ecommerce_video

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.emo_2025_3002_ecommerce_video.models.RatingDto
import com.example.emo_2025_3002_ecommerce_video.models.RatingsState
import com.example.emo_2025_3002_ecommerce_video.vm.ProductsWithReviewsViewModel

@Composable
fun RatingsScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: ProductsWithReviewsViewModel,
    onNavigateBack: () -> Unit
) {
    val state by viewModel.ratingsByProductState.collectAsStateWithLifecycle()
    RatingsScreen(state = state, onRemove = { ratingId ->
        viewModel.poistaArvostelu(ratingId)
    }, onNavigateBack = onNavigateBack)

    /*
    * Miten tässä voi hakea datan backendista osoitteesta localhost:portti/api/products/{productId}
    Dataa ei voi hakea viewmodelin init-blokissa, koska initiä ei suoriteta tämän komponentin latautuessa
    * koska sama viewmodel-instanssi jaetaan kahden screenin kesken
    *
    * tutustu LaunchedEffectiin
    *
    * * */

    LaunchedEffect(Unit) {
        viewModel.haeArvostelutTuotteelle()
    }


}

@Composable
@OptIn(ExperimentalMaterial3Api::class)

fun RatingsScreen(
    modifier: Modifier = Modifier,
    state: RatingsState,
    onRemove: (Int) -> Unit,
    onNavigateBack: () -> Unit

) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text("Reviews")
        }, navigationIcon = {
            IconButton(onClick = onNavigateBack) {
                Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Open Menu")
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
                        RatingItem(item = rating, onRemove = onRemove)
                    }
                }
            }
        }
    }
}

@Composable
fun RatingItem(modifier: Modifier = Modifier, item: RatingDto, onRemove: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {

                RatingBar(rating = item.rating, reviewCount = 1, showReviewCount = false)
                // https://stackoverflow.com/questions/76218477/how-to-align-view-at-end-in-row-jetpack-compose

                Spacer(modifier = Modifier.weight(1f))


                IconButton(
                    onClick = {
                        onRemove(item.id)
                    },
                ) {
                    Icon(Icons.Filled.Delete, contentDescription = "Remove Review")
                }

            }
            Text(item.message ?: "")
        }
    }
}