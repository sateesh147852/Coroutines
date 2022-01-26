package com.coroutines.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coroutines.R
import com.coroutines.databinding.RetroItemBinding
import com.coroutines.model.RetroPhoto

class RetroAdapter(private val retroPhoto: List<RetroPhoto>) :
    RecyclerView.Adapter<RetroAdapter.RetroViewHolder>() {

    private lateinit var context: Context

    class RetroViewHolder(val binding: RetroItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetroViewHolder {
        context = parent.context
        val binding = RetroItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RetroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RetroViewHolder, position: Int) {
        holder.binding.tvTitle.text = retroPhoto[position].title
        holder.binding.tvId.text = retroPhoto[position].id.toString()
        if (retroPhoto[position].url.isNotEmpty()) {
            Glide.with(context)
                //.load(retroPhoto[position].thumbnailUrl)
                .load(R.mipmap.ic_launcher)
                .into(holder.binding.ivImage)
        }
    }

    override fun getItemCount(): Int {
        return retroPhoto.size
    }
}