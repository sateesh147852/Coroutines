package com.coroutines.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.coroutines.databinding.ActivityMainBinding
import com.coroutines.viewmodel.MainViewModel
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //runJobOne()    // join
        //runJobTwo()    // cancel
        //runJobThree()    // isActive
        runJobFour()    // withTimeout
    }

    private fun runJobOne() {

        val job = GlobalScope.launch(Dispatchers.IO) {
            repeat(5) {
                Log.i(TAG, "coroutine is still running.....")
                delay(1000)
            }

        }

        runBlocking {
            job.join()
            Log.i(TAG, "Main thread is continuing....")
        }
    }

    private fun runJobTwo() {

        val job = GlobalScope.launch(Dispatchers.IO) {
            repeat(5) {
                Log.i(TAG, "coroutine is still running.....")
                delay(1000)
            }
        }

        runBlocking {
            delay(3000)
            job.cancel()
            Log.i(TAG, "Main thread is continuing....")
        }

    }

    private fun runJobThree() {

        val job = GlobalScope.launch(Dispatchers.Default) {
            for (number in 30..40) {
                if (isActive)
                Log.i(TAG, "Result for i = $number : ${findFib(number)}")
            }
            Log.i(TAG, "Ending long running calculation")
        }

        runBlocking {
            delay(3000)
            job.cancel()
        }
    }

    private fun runJobFour() {

        GlobalScope.launch(Dispatchers.Default) {
            withTimeout(3000){
                for (number in 30..40) {
                    Log.i(TAG, "runJobFour: $number")
                    if (isActive)
                        Log.i(TAG, "Result for i = $number : ${findFib(number)}")
                    else
                        break
                }
                Log.i(TAG, "Ending long running calculation")
            }
        }

    }


    private fun findFib(number: Int): Long {
        return when (number) {
            0 -> 0
            1 -> 1
            else -> findFib(number - 1) + findFib(number - 2)
        }
    }
}