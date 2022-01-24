package com.coroutines.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.coroutines.databinding.ActivityMainBinding
import com.coroutines.viewmodel.MainViewModel
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    //Run blocking : 1) it will block main thread 2) if we want to call suspend from main thread

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Main thread will be blocked for 5 seconds
        /*runBlocking {
            Log.i(TAG, Thread.currentThread().name)
            doNetworkCall()
        }*/

        //Main thread will not be blocked for 5 seconds
        /*GlobalScope.launch(Dispatchers.Main){
            Log.i(TAG, "Api called")
            doNetworkCall()
            Log.i(TAG, "got response from api")
        }*/

        ///////////////////////////////////////////////////////////////

        runBlocking {
            /*
            This will take 10 seconds
            doNetworkCall()
            doNetworkCall()
            Log.i(TAG, "Network call finished")*/

            //both will take only five seconds
            launch {
                doNetworkCall()
                Log.i(TAG, "Network call done 1 "+Thread.currentThread().name)
            }
            launch {
                doNetworkCall()
                Log.i(TAG, "Network call done 2 "+Thread.currentThread().name)
            }
            delay(7000)
            Log.i(TAG, "run blocking done "+Thread.currentThread().name)
        }
    }

    private suspend fun doNetworkCall() {
        delay(8000)
    }

}