package com.practicum.applastfm.lastfmapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LastFmApi {

    @GET("/2.0/?method=artist.gettoptracks&api_key=$API_KEY&format=json&limit=3")
    fun getTopTracksArtist(@Query("artist") artist: String): Call<TracksResponse>

    @GET("/2.0/?method=artist.getinfo&api_key=$API_KEY&format=json")
    fun getBiographyArtist(@Query("artist") artist: String): Call<ArtistResponse>

    companion object {
        const val LASTFM_BASE_URL = "https://ws.audioscrobbler.com"
        private const val API_KEY = "aca5bbff6b2ab302857d91a3f50dbe4f"
    }
}