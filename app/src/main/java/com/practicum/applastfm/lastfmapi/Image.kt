package com.practicum.applastfm.lastfmapi

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("#text") val url: String
)
