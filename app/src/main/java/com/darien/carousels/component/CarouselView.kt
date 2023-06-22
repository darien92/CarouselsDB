package com.darien.carousels.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.darien.carousels.model.CarouselItemDM
import com.darien.carousels.utils.recomposeHighlighter

@Composable
fun CarouselView(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    elements: List<CarouselItemDM>,
    onListReachEnd: () -> Unit,
    onItemClicked: (Int) -> Unit
) {
    Column(modifier = modifier
        .padding(8.dp)
        .height(120.dp)
        .recomposeHighlighter()
    ) {
        LazyRow {
            itemsIndexed(elements) { index, item ->
                if (index == elements.lastIndex) {
                    onListReachEnd()
                }
                CarouselElement(carouselItemDM = item) { currId ->
                    onItemClicked(currId)
                }
            }
            if (isLoading) {
                item {
                    CircularProgressIndicator()
                }
            }
        }
    }
}