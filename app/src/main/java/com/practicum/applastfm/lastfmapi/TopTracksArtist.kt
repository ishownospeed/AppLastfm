package com.practicum.applastfm.lastfmapi

import com.google.gson.annotations.SerializedName

data class TopTracksArtist (
    @SerializedName("track") val topTracks: List<TrackFromTop>
)
