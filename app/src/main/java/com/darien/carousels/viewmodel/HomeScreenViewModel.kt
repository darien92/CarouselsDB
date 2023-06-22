package com.darien.carousels.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darien.carousels.repository.ItemsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val repository: ItemsRepository): ViewModel(){
    private val _uiState = MutableStateFlow(HomeScreenState())
    val uiState: StateFlow<HomeScreenState> = _uiState.asStateFlow()
    var firstCarouselPage = 0
    var secondCarouselPage = 3

    private val coroutineExceptionHandler by lazy {
        CoroutineExceptionHandler { _, ex ->
            Log.e("SaveViewModel", ex.message.toString())
        }
    }

    private val viewModelSafeScope by lazy {
        viewModelScope + Dispatchers.IO + coroutineExceptionHandler
    }

    fun requestDataInitially() {
        viewModelSafeScope.launch {
            _uiState.update {
                it.copy(isLoading = true, initiallyLoaded = true)
            }
            val firstItemsResponse = async { repository.getItems(0) }
            val secondItemsResponse = async { repository.getItems(3) }
            val firstItemsList = _uiState.value.firstCarousel
            val secondItemsList = _uiState.value.secondCarousel
            firstItemsList.addAll(firstItemsResponse.await())
            secondItemsList.addAll(secondItemsResponse.await())

            _uiState.update {
                it.copy(isLoading = false, firstCarousel = firstItemsList, secondCarousel = secondItemsList)
            }
        }
    }

    fun updateFirstCarousel() {
        viewModelSafeScope.launch {
            if (firstCarouselPage < 2) {
                _uiState.update {
                    it.copy(isFistCarouselLoading = true)
                }
                firstCarouselPage ++

                val firstItemsList = _uiState.value.firstCarousel
                firstItemsList.addAll(repository.getItems(firstCarouselPage))
                _uiState.update {
                    it.copy(isFistCarouselLoading = false, firstCarousel = firstItemsList)
                }
            }
        }
    }

    fun updateSecondCarousel() {
        viewModelSafeScope.launch {
            if (secondCarouselPage < 4) {
                _uiState.update {
                    it.copy(isSecondCarouselLoading = true)
                }
                secondCarouselPage ++

                val secondItemsList = _uiState.value.secondCarousel
                secondItemsList.addAll(repository.getItems(secondCarouselPage))
                _uiState.update {
                    it.copy(isSecondCarouselLoading = false, secondCarousel = secondItemsList)
                }
            }
        }
    }
}