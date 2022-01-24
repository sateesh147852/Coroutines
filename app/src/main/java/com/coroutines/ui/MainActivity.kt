package com.coroutines.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.coroutines.databinding.ActivityMainBinding
import com.coroutines.viewmodel.MainViewModel
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    //Main = runs on MainThread
    //IO = runs on workerThread
    //Default = runs on background thread. it is used when performing complex operations which is taking more time
    //Confined

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*GlobalScope.launch(Dispatchers.Main){
            Log.i(TAG, Thread.currentThread().name)
            Toast.makeText(this@MainActivity,"dummy text",Toast.LENGTH_SHORT).show()
            binding.tvText.text = "Dummy Text"
        }*/

        /*GlobalScope.launch(Dispatchers.IO){
            Log.i(TAG, Thread.currentThread().name)
            delay(3000)
            withContext(Dispatchers.Main){
                Log.i(TAG, Thread.currentThread().name)
                Toast.makeText(this@MainActivity,"dummy text",Toast.LENGTH_SHORT).show()
                binding.tvText.text = "Dummy Text"
            }
        }*/

        /*GlobalScope.launch(Dispatchers.Default){
            Log.i(TAG, Thread.currentThread().name)
            delay(3000)
            withContext(Dispatchers.Main){
                Log.i(TAG, Thread.currentThread().name)
                Toast.makeText(this@MainActivity,"dummy text",Toast.LENGTH_SHORT).show()
                binding.tvText.text = "Dummy Text"
            }
        }*/

        GlobalScope.launch(Dispatchers.Unconfined){
            Log.i(TAG, Thread.currentThread().name)
            delay(3000)
            withContext(Dispatchers.Main){
                Log.i(TAG, Thread.currentThread().name)
                Toast.makeText(this@MainActivity,"dummy text",Toast.LENGTH_SHORT).show()
                binding.tvText.text = "Dummy Text"
            }
        }

    }
}