package com.practicum.viewmodel.viewmodels

import android.content.Context
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practicum.model.lastfmapi.LastFmApi
import com.practicum.model.models.TrackFromTop
import com.practicum.model.models.TracksResponse
import com.practicum.viewmodel.TrackAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class BestTracksViewModel @Inject constructor(private val provideLastFmApi: LastFmApi) : ViewModel() {

    private val tracks = mutableListOf<TrackFromTop>()
    val adapterListTracks = TrackAdapter(tracks)

    private val tracksLiveMutable = MutableLiveData<List<TrackFromTop>>()
    val tracksMutableLiveData: MutableLiveData<List<TrackFromTop>> = tracksLiveMutable

    fun responseServerTopTracksArtist(
        context: Context,
        editText: EditText
    ) {
        provideLastFmApi
            .getTopTracksArtist(editText.text.toString())
            .enqueue(object : Callback<TracksResponse> {
                override fun onResponse(
                    call: Call<TracksResponse>,
                    response: Response<TracksResponse>
                ) {
                    if (response.isSuccessful) {
                        tracks.clear()
                        val tracks = response.body()?.tracks
                        if (tracks?.topTracks?.isNotEmpty() == true) {
                            adapterListTracks.addAllTracks(tracks.topTracks)
                            tracksLiveMutable.value = tracks.topTracks
                        } else {
                            Toast.makeText(context, "Ничего не нашлось", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<TracksResponse>, t: Throwable) {
                    Toast.makeText(context, "Проверьте подключение к интернету", Toast.LENGTH_SHORT)
                        .show()
                }

            })
    }

}