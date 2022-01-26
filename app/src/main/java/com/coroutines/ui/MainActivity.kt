package com.coroutines.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.coroutines.adapter.RetroAdapter
import com.coroutines.databinding.ActivityMainBinding
import com.coroutines.model.RetroPhoto
import com.coroutines.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.getPhotos().observe(this, {
            updateRecyclerView(it)
        })

        viewModel.getError().observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun updateRecyclerView(list: List<RetroPhoto>) {
        binding.rvItems.layoutManager = LinearLayoutManager(this)
        binding.rvItems.adapter = RetroAdapter(list)
    }
}