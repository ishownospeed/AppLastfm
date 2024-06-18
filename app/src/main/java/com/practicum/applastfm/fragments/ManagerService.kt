package com.practicum.applastfm.fragments

import com.practicum.applastfm.lastfmapi.LastFmApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ManagerService {

    private val service: LastFmApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(LastFmApi.LASTFM_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(LastFmApi::class.java)
    }

    fun getServiceLastFmApi(): LastFmApi {
        return service
    }
}