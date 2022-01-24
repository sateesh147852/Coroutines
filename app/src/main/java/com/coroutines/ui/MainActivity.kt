package com.coroutines.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.coroutines.databinding.ActivityMainBinding
import com.coroutines.viewmodel.MainViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btNext.setOnClickListener {
            Intent(this, SecondActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        GlobalScope.launch {
            /*while (true) {
                Log.i(TAG, "Running")
            }*/
            val answer1 = doNetworkCall1()
            val answer2 = doNetworkCall2()
            Log.i(TAG, answer1 + answer2)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("onDestroy", "onDestroy: ")
    }

    private suspend fun doNetworkCall1() : String  {
        delay(4000)
        return "Answer received from one"
    }

    private suspend fun doNetworkCall2() : String {
        delay(4000)
        return "Answer received from two"
    }
}