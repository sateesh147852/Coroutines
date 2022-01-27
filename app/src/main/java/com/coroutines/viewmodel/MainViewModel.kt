package com.coroutines.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coroutines.api.RetrofitHelper
import com.coroutines.model.RetroPhoto
import kotlinx.coroutines.*
import retrofit2.Response

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

        val response: Result<Deferred<Response<List<RetroPhoto>>>> = kotlin.runCatching {
            RetrofitHelper.getApiService().getAllPhotos()
        }

        if(response.isSuccess){

            response.map {
                withContext(Dispatchers.Main){
                    photos.value = it.await().body()
                }
            }

        }
        else{
            response.onFailure {
                withContext(Dispatchers.Main){
                    error.value = it.message
                }
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