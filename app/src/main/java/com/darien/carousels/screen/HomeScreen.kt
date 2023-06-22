package com.darien.carousels.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.darien.carousels.component.CarouselView
import com.darien.carousels.component.LoadingView
import com.darien.carousels.utils.AppScreens
import com.darien.carousels.viewmodel.HomeScreenViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = state.initiallyLoaded) {
        if (!state.initiallyLoaded) {
            viewModel.requestDataInitially()
        }
    }
    Column(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            LoadingView()
        } else {
            CarouselView(
                elements = state.firstCarousel,
                isLoading = state.isFistCarouselLoading,
                onListReachEnd = {
                    viewModel.updateFirstCarousel()
                }
            ) { id ->
                navigateTo(navController = navController, id = id)
            }
            Spacer(modifier = Modifier.size(12.dp))

            CarouselView(
                elements = state.secondCarousel,
                isLoading = state.isSecondCarouselLoading,
                onListReachEnd = {
                    viewModel.updateSecondCarousel()
                }
            ) { id ->
                navigateTo(navController = navController, id = id)
            }
        }
    }
}

private fun navigateTo(navController: NavController, id: Int) {
    navController.navigate(AppScreens.DetailsScreen.name + "/" + id)
}