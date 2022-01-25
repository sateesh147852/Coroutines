package com.coroutines.ui

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.coroutines.databinding.ActivityMainBinding
import com.coroutines.viewmodel.MainViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private val TAG = "MainActivity"
    private val job = Job()
    private lateinit var handler: Handler

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        launch {
            Log.i(TAG, "coroutine is running on ${Thread.currentThread().name}")
        }

        async {
            Log.i(TAG, "coroutine is running on ${Thread.currentThread().name}")
        }
    }

    override val coroutineContext: CoroutineContext
        //get() = job + Dispatchers.Main
        //get() = job + Dispatchers.Default
        get() = job + Dispatchers.IO
}