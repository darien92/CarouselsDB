package com.darien.carousels.repository

import com.darien.carousels.model.CarouselItemDM
import kotlinx.coroutines.delay

class ItemsRepository {

    suspend fun getItems(page: Int): List<CarouselItemDM> {
        delay(3000)
        val elements: MutableList<CarouselItemDM> = mutableListOf()
        val fromIndex = page * 15
        elements.addAll(generateList(fromIndex, fromIndex + 14))
        return elements
    }

    private fun generateList(start: Int, end: Int): List<CarouselItemDM> {
        val elementsList: MutableList<CarouselItemDM> = mutableListOf()
        for (i in start..end) {
            elementsList.add(CarouselItemDM(id = i, name = "Element $i"))
        }
        return elementsList
    }
}