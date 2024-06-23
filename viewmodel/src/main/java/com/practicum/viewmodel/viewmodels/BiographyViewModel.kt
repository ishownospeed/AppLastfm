package com.practicum.viewmodel.viewmodels

import android.content.Context
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.practicum.model.lastfmapi.LastFmApi
import com.practicum.model.models.ArtistResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class BiographyViewModel @Inject constructor(private val provideLastFmApi: LastFmApi) : ViewModel() {

    private val nameArtistLiveMutable = MutableLiveData<String>()
    val nameMutableLiveData: MutableLiveData<String> = nameArtistLiveMutable

    private val bioArtistLiveMutable = MutableLiveData<String>()
    val bioMutableLiveData: MutableLiveData<String> = bioArtistLiveMutable

    private val imageArtistLiveMutable = MutableLiveData<String>()
    val imageMutableLiveData: MutableLiveData<String> = imageArtistLiveMutable


    fun responseServerBiographyArtist(
        context: Context,
        editText: EditText,
        pictureArtist: ImageView,
        nameArtist: TextView,
        bioArtist: TextView
    ) {
        provideLastFmApi
            .getBiographyArtist(editText.text.toString())
            .enqueue(object : Callback<ArtistResponse> {
                override fun onResponse(
                    call: Call<ArtistResponse>,
                    response: Response<ArtistResponse>
                ) {
                    if (response.isSuccessful) {
                        val artist = response.body()?.artist

                        if (artist == null) {
                            Toast.makeText(context, "Ничего не нашлось", Toast.LENGTH_SHORT).show()
                            return
                        }

                        Glide.with(context)
                            .load(artist.images[1].url)
                            .fitCenter()
                            .into(pictureArtist)

                        nameArtist.text = artist.name
                        bioArtist.text = artist.bio.summary

                        nameArtistLiveMutable.value = artist.name
                        bioArtistLiveMutable.value = artist.bio.summary
                        imageArtistLiveMutable.value = artist.images[1].url
                    }
                }

                override fun onFailure(call: Call<ArtistResponse>, t: Throwable) {
                    Toast.makeText(context, "Проверьте подключение к интернету", Toast.LENGTH_SHORT)
                        .show()
                }

            })
    }

}