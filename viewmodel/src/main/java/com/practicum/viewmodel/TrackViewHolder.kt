package com.practicum.viewmodel

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practicum.model.models.TrackFromTop

class TrackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val image: ImageView = itemView.findViewById(R.id.trackImage)
    private val track: TextView = itemView.findViewById(R.id.trackName)

    fun bind(item: TrackFromTop) {
        Glide.with(itemView)
            .load(item.images[0].url)
            .placeholder(R.color.violet)
            .fitCenter()
            .into(image)

        track.text = item.name
    }
}