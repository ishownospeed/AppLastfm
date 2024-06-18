package com.practicum.applastfm.lastfmapi

import com.google.gson.annotations.SerializedName

data class TracksResponse(
    @SerializedName("toptracks") val tracks: TopTracksArtist
)
