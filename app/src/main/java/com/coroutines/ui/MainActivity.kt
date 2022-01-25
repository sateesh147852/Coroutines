package com.coroutines.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.coroutines.databinding.ActivityMainBinding
import com.coroutines.viewmodel.MainViewModel
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GlobalScope.launch(Dispatchers.Main) {

            val time = measureTimeMillis {
                val answer1 = withContext(Dispatchers.IO) { doNetworkCall1() }
                Log.i(TAG, "answer1 is  : $answer1")
                val answer2 = withContext(Dispatchers.IO) { doNetworkCall2() }
                Log.i(TAG, "answer2 is  : $answer2")
            }
            Log.i(TAG, "Request took $time")
        }

    }

    private suspend fun doNetworkCall1(): String {
        delay(3000)
        return "Answer is one"
    }

    private suspend fun doNetworkCall2(): String {
        delay(3000)
        return "Answer is two"
    }
}