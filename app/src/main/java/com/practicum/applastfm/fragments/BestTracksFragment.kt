package com.practicum.applastfm.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.practicum.applastfm.R
import com.practicum.applastfm.lastfmapi.TrackFromTop
import com.practicum.applastfm.lastfmapi.TracksResponse
import com.practicum.applastfm.databinding.FragmentBestTracksBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BestTracksFragment : Fragment(R.layout.fragment_best_tracks) {

    private lateinit var binding: FragmentBestTracksBinding

    private val tracks = mutableListOf<TrackFromTop>()
    private val adapterListTracks = TrackAdapter(tracks)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBestTracksBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listBestTracks.adapter = adapterListTracks

        binding.buttonBackForBestTracks.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.buttonBestTracks.setOnClickListener {
            if (binding.etBestTracks.text.isNotEmpty()) {
                hideKeyBoard()
                responseServerTopTracksArtist()
            }
        }

    }

    private fun responseServerTopTracksArtist() {
        ManagerService.getServiceLastFmApi()
            .getTopTracksArtist(binding.etBestTracks.text.toString())
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
                        } else {
                            Toast.makeText(context, "Ничего не нашлось", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<TracksResponse>, t: Throwable) {
                    Toast.makeText(context, "Проверьте подключение к интернету", Toast.LENGTH_SHORT).show()
                }

            })
    }

    private fun hideKeyBoard() {
        activity?.currentFocus?.let { view ->
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}