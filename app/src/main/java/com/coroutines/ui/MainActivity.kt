package com.coroutines.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.coroutines.databinding.ActivityMainBinding
import com.coroutines.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private val job = Job()
    private val coroutineIOContext : CoroutineContext = job + Dispatchers.IO
    private val coroutineMainContext : CoroutineContext = job + Dispatchers.Main
    private val coroutineDefaultContext : CoroutineContext = job + Dispatchers.Default

    private val coroutineIOScope : CoroutineScope = CoroutineScope(coroutineIOContext)
    private val coroutineMainScope : CoroutineScope = CoroutineScope(coroutineMainContext)
    private val coroutineDefaultScope : CoroutineScope = CoroutineScope(coroutineDefaultContext)

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        coroutineMainScope.launch {
            Log.i(TAG, "coroutine is running on ${Thread.currentThread().name} ")
        }

        coroutineIOScope.launch {
            Log.i(TAG, "coroutine is running on ${Thread.currentThread().name} ")
        }

        coroutineDefaultScope.launch {
            Log.i(TAG, "coroutine is running on ${Thread.currentThread().name} ")
        }

    }
}