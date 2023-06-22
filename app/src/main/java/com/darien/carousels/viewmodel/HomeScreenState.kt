package com.darien.carousels.viewmodel

import com.darien.carousels.model.CarouselItemDM

data class HomeScreenState(
    val isLoading: Boolean = false,
    val initiallyLoaded: Boolean = false,
    val isFistCarouselLoading: Boolean = false,
    val isSecondCarouselLoading: Boolean = false,
    val firstCarousel: MutableList<CarouselItemDM> = ArrayList(),
    val secondCarousel: MutableList<CarouselItemDM> = ArrayList()
)
