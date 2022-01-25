package com.coroutines.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.coroutines.databinding.ActivityMainBinding
import com.coroutines.viewmodel.MainViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //runJobOne()
        //runJobTwo()   // This is not a good practice
        runJobThree()   // This is a good practice

    }

    private fun runJobOne() {
        GlobalScope.launch {
            val time = measureTimeMillis {
                val answer1 = doNetworkCall1()
                val answer2 = doNetworkCall2()
                Log.i(TAG, "Answer 1 is: $answer1")
                Log.i(TAG, "Answer 2 is: $answer2")
            }
            Log.i(TAG, "request took : $time seconds")
        }
    }

    private fun runJobTwo() {
        GlobalScope.launch {
            val time = measureTimeMillis {
                var answer1: String? = null
                var answer2: String? = null

                val job1 = launch {
                    answer1 = doNetworkCall1()
                }
                val job2 = launch {
                    answer2 = doNetworkCall2()
                }
                job1.join()
                job2.join()

                Log.i(TAG, "Answer 1 is: $answer1")
                Log.i(TAG, "Answer 2 is: $answer2")
            }
            Log.i(TAG, "request took : $time seconds")
        }
    }

    private fun runJobThree() {

        GlobalScope.launch {

            val time = measureTimeMillis {
                val answer1 = async { doNetworkCall1() }
                val answer2 = async { doNetworkCall2() }
                Log.i(TAG, "response is ${answer1.await()}")
                Log.i(TAG, "response is ${answer2.await()}")
            }
            Log.i(TAG, "Request took : $time milliseconds")

        }


    }

    private suspend fun doNetworkCall1(): String {
        delay(3000)
        return "Network answer one"
    }

    private suspend fun doNetworkCall2(): String {
        delay(7000)
        return "Network answer two"
    }
}