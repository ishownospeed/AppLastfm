package com.practicum.model.models

import com.google.gson.annotations.SerializedName

data class TrackFromTop(
    @SerializedName("name") val name: String,
    @SerializedName("image") val images: List<Image>
)
