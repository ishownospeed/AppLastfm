package com.practicum.applastfm.fragments

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practicum.applastfm.lastfmapi.TrackFromTop

class TrackAdapter(private var tracks: MutableList<TrackFromTop>) :
    RecyclerView.Adapter<TrackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        return TrackViewHolder(parent)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bind(tracks[position])
    }

    override fun getItemCount(): Int {
        return tracks.size
    }

    fun addAllTracks(trackList: List<TrackFromTop>) {
        tracks.addAll(trackList)
        this.notifyDataSetChanged()
    }
}