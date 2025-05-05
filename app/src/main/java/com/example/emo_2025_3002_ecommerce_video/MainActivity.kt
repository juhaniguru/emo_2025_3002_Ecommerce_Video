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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
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

                        composable("ProductsWithAvgRatings") { navGraph ->
                            val viewmodel =
                                navGraph.SharedViewModel<ProductsWithReviewsViewModel>(navController)
                            ProductsWithAvgRatingsRoot(viewmodel = viewmodel, onNavigate = {
                                navController.navigate("Ratings")
                            })
                        }
                        composable("Ratings") { navGraph ->
                            val viewmodel =
                                navGraph.SharedViewModel<ProductsWithReviewsViewModel>(navController)
                            RatingsScreenRoot(viewModel = viewmodel)
                        }
                    }
                }
            }
        }
    }
}


@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.SharedViewModel(navController: NavController): T {
    // route of the current navigation graph
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }

    return hiltViewModel(parentEntry)

}

