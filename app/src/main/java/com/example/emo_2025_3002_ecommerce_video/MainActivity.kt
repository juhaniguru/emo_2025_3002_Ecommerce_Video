package com.example.emo_2025_3002_ecommerce_video

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.emo_2025_3002_ecommerce_video.ui.theme.Emo_2025_3002_Ecommerce_VideoTheme
import com.example.emo_2025_3002_ecommerce_video.vm.ProductsWithReviewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Emo_2025_3002_Ecommerce_VideoTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, "productsWithReviewFeature") {

                    navigation("ProductsWithAvgRatings", route = "productsWithReviewFeature") {

                        composable("ProductsWithAvgRatings") {
                            val viewmodel = hiltViewModel<ProductsWithReviewsViewModel>()
                            ProductsWithAvgRatingsRoot(viewmodel = viewmodel)
                        }
                        composable("Ratings") {
                            val viewmodel = hiltViewModel<ProductsWithReviewsViewModel>()
                            Text("Terve")
                        }
                    }
                }
            }
        }
    }
}

