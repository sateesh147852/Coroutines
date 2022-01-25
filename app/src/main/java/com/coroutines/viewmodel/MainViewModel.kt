package com.coroutines.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val TAG = "MainViewModel"
    private var count  = 0

    init {
        viewModelScope.launch {
            while (true) {
                delay(1000)
                Log.i(TAG, "Coroutine is still running ${count++}")
            }
        }
    }
}