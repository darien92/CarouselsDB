package com.darien.carousels.db

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class SaveViewModel @Inject constructor
    (
    private val DBRepo: DBRepository
) : ViewModel() {

    private val coroutineExceptionHandler by lazy {
        CoroutineExceptionHandler { _, ex ->
            Log.e("SaveViewModel", ex.message.toString())
        }
    }

    private val viewModelSafeScope by lazy {
        viewModelScope + Dispatchers.IO + coroutineExceptionHandler
    }

    fun setStatus(id: Int, isSaved: Boolean) {
        viewModelSafeScope.launch {
            DBRepo.saveStatus(id = id, isSaved = isSaved)
        }
    }

    fun listenToSaveEvents(id: Int, onStatusReceived: (Boolean) -> Unit) {
        viewModelSafeScope.launch {
            DBRepo.getStatus(id = id).collect { isSaved ->
                onStatusReceived(isSaved)
            }
        }
    }
}