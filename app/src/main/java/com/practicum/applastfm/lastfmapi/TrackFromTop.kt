package com.practicum.applastfm.lastfmapi

import com.google.gson.annotations.SerializedName

data class TrackFromTop(
    @SerializedName("name") val name: String,
    @SerializedName("image") val images: List<Image>
)
