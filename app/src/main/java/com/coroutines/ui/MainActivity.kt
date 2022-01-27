package com.coroutines.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.coroutines.databinding.ActivityMainBinding
import com.coroutines.viewmodel.MainViewModel
import kotlinx.coroutines.*
import java.lang.ArithmeticException
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private lateinit var handler: CoroutineExceptionHandler
    private val job = Job()
    private lateinit var coroutineContext: CoroutineContext
    private lateinit var coroutineScope: CoroutineScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handler = CoroutineExceptionHandler { context: CoroutineContext, exception: Throwable ->
            Log.i(TAG, "Exception has been handled")
        }

        coroutineContext = job + Dispatchers.IO + handler
        coroutineScope = CoroutineScope(coroutineContext)

        val job = lifecycleScope.launch(handler) {
            Log.i(TAG, "Throwing exception....")
            //10/2
            throw Exception()
        }

        runBlocking {
            job.join()
            Log.i(TAG, "Job joining failed.....")
        }
    }


}