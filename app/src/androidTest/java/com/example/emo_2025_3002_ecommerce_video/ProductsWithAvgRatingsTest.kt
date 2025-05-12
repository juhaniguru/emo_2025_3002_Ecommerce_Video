package com.example.emo_2025_3002_ecommerce_video

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.emo_2025_3002_ecommerce_video.models.ProductsWithAvgRatingsState
import com.example.emo_2025_3002_ecommerce_video.ui.theme.Emo_2025_3002_Ecommerce_VideoTheme
import org.junit.Rule
import org.junit.Test

class ProductsWithAvgRatingsTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loadingVisibleTest() {
        composeTestRule.setContent {
            Emo_2025_3002_Ecommerce_VideoTheme {
                val state = ProductsWithAvgRatingsState(loading = true)
                ProductsWithAvgRatingsScreen(state=state, onNavigate = {})
            }
        }

        composeTestRule.onNodeWithTag("loader").assertExists()
    }
}