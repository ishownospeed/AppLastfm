package com.practicum.applastfm.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practicum.applastfm.R
import com.practicum.applastfm.databinding.TrackViewBinding
import com.practicum.applastfm.lastfmapi.TrackFromTop

class TrackViewHolder(
    parent: ViewGroup,
    binding: TrackViewBinding = TrackViewBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
    )
) : RecyclerView.ViewHolder(binding.root) {

    private val image: ImageView = binding.trackImage
    private val track: TextView = binding.trackName

    fun bind(item: TrackFromTop) {
        Glide.with(itemView)
            .load(item.images[0].url)
            .placeholder(R.color.violet)
            .fitCenter()
            .into(image)

        track.text = item.name
    }
}