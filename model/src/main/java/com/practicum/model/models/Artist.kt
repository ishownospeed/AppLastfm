package com.practicum.model.models

import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("image") val images: List<Image>,
    val name: String,
    val bio: Bio
)
