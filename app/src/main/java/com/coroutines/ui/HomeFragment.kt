package com.coroutines.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.coroutines.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private val TAG : String = HomeFragment::class.simpleName.toString()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            Log.i(TAG, "coroutine is running on ${Thread.currentThread().name}")
        }

        lifecycleScope.launch {
            Log.i(TAG, "coroutine is running on ${Thread.currentThread().name}")
        }

        GlobalScope.launch(Dispatchers.IO) {
            Log.i(TAG, "coroutine is running on ${Thread.currentThread().name}")
        }
    }

    companion object {

        private var homeFragment: HomeFragment? = null

        fun getInstance(): HomeFragment {
            if (homeFragment == null) {
                synchronized(this) {
                    homeFragment = HomeFragment()
                }
            }
            return  homeFragment!!
        }
    }
}