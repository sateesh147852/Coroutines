package com.coroutines.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.coroutines.databinding.ActivityMainBinding
import com.coroutines.model.User
import com.coroutines.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //runJobOne()
        //runJobTwo()
        //runJobThree()
        runJobFour()
    }

    private fun runJobOne() {
        runCatching {
            Log.i(TAG, "onCreate: ${Thread.currentThread().name}")
            getRandomNumber()
        }.onSuccess {
            Log.i(TAG, "$it")
        }.onFailure {
            Log.i(TAG, "${it.message}")
        }
    }

    private fun runJobTwo() {
        val result: Result<Int> = runCatching {
            getRandomNumber()
        }
        if (result.isSuccess) {
            result.map {
                Log.i(TAG, "$it")
            }

        } else {
            result.onFailure {
                Log.i(TAG, "${it.message} odd number")

            }
        }
    }

    private fun runJobThree(){

        val result : Result<Int> = runCatching {
            getRandomNumber()
        }
        if (result.isSuccess){

            Log.i(TAG, "newResult: onSuccess")
          val newResult: Result<String> =   result.mapCatching {
                it.toString()
            }

            if (newResult.isSuccess){
                newResult.onSuccess {
                    Log.i(TAG, "newResult: $it ${it.length}")
                }
                newResult.onFailure {
                    Log.i(TAG, "newResult: ${it.message}")
                }
            }

        }

        result.onFailure {
            Log.i(TAG, "${it.message}")
        }

    }

    private fun runJobFour() {

        val result = kotlin.runCatching {
            getRandomNumber()
        }
        if (result.isSuccess){
            Log.i(TAG, "success")
            result.onSuccess {

                val newResult: Result<User> = result.mapCatching {
                    User("Sateesh",it)
                }

                if (newResult.isSuccess){

                    newResult.map {
                        Log.i(TAG, "$it")
                    }

                }
                else{
                    newResult.onFailure {
                        Log.i(TAG, "${it.message}")
                    }
                }

            }
        }
        else{
            result.onFailure {
                Log.i(TAG, "${it.message}")
            }
        }

    }

    private fun getRandomNumber(): Int {
        val randomNumber = (1..20).shuffled().first()
        if (randomNumber % 2 == 0)
            return randomNumber
        else
            throw Exception("The random number is odd.")
    }
}