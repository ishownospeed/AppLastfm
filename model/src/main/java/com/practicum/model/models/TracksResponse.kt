package com.practicum.model.models

import com.google.gson.annotations.SerializedName

data class TracksResponse(
    @SerializedName("toptracks") val tracks: TopTracksArtist
)
