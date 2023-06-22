package com.darien.carousels.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.darien.carousels.model.CarouselItemDM
import com.darien.carousels.utils.recomposeHighlighter

@Composable
fun CarouselElement(
    modifier: Modifier = Modifier,
    carouselItemDM: CarouselItemDM,
    onItemClicked: (Int) -> Unit
) {
    Card(
        modifier = modifier
            .width(80.dp)
            .clickable {
                onItemClicked(carouselItemDM.id)
            }
            .recomposeHighlighter()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = carouselItemDM.name)
            Spacer(modifier = Modifier.size(8.dp))
            SaveButton(
                modifier = Modifier.size(60.dp),
                id = carouselItemDM.id
            )
            Spacer(modifier = Modifier.size(8.dp))
        }
    }
}