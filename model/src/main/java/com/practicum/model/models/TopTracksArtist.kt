package com.practicum.model.models

import com.google.gson.annotations.SerializedName

data class TopTracksArtist (
    @SerializedName("track") val topTracks: List<TrackFromTop>
)
