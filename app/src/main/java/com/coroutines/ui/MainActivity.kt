package com.coroutines.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
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

        lifecycleScope.launch {
            while (true){
                Log.i(TAG, "coroutines is still running.....")
                delay(1000)
            }
        }

        lifecycleScope.launch {
            delay(5000)
            Intent(this@MainActivity, SecondActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }


}