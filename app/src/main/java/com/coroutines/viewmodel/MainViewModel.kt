package com.coroutines.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coroutines.api.RetrofitHelper
import com.coroutines.model.RetroPhoto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val TAG = "MainViewModel"
    private val photos: MutableLiveData<List<RetroPhoto>> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()
    private var job : Job? = null

    init {
        job = viewModelScope.launch(Dispatchers.IO) {
            getAllPhotos()
        }
    }

    private suspend fun getAllPhotos() {

        val response = RetrofitHelper.getApiService().getAllPhotos()

        withContext(Dispatchers.Main){
            if (response.isSuccessful) {
                Log.i(TAG, "getAllPhotos: ${response.body()?.size}")
                photos.value = response.body()
            } else {
                error.value = response.errorBody().toString()
            }
        }

    }

    fun getPhotos(): LiveData<List<RetroPhoto>> {
        return photos
    }

    fun getError(): MutableLiveData<String> {
        return error
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}