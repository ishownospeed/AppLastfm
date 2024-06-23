package com.practicum.model.models

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("#text") val url: String
)
