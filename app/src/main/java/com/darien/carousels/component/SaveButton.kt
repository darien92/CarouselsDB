package com.darien.carousels.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.darien.carousels.R
import com.darien.carousels.db.SaveViewModel

@Composable
fun SaveButton(
    modifier: Modifier,
    id: Int,
    viewModel: SaveViewModel = hiltViewModel()
) {
    var initialized by remember { mutableStateOf(false) }

    var saved by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = initialized) {
        if (!initialized) {
            initialized = true
            viewModel.listenToSaveEvents(id = id) {
                saved = it
            }
        }
    }
    Column(
        modifier = modifier.clickable {
            viewModel.setStatus(id, !saved)
        }
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = if(saved) painterResource(id = R.drawable.filled_thum) else painterResource(id = R.drawable.unfilled_thumb),
            contentDescription = "Save"
        )
    }
}

@Preview
@Composable
fun SaveButtonPreview() {
    MaterialTheme {
        SaveButton(
            modifier = Modifier.size(80.dp),
            id = 0
        )
    }
}