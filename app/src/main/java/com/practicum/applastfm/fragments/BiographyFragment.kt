package com.practicum.applastfm.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.practicum.applastfm.R
import com.practicum.applastfm.databinding.FragmentBiographySearchBinding
import com.practicum.applastfm.lastfmapi.ArtistResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BiographyFragment : Fragment(R.layout.fragment_biography_search) {

    private lateinit var binding: FragmentBiographySearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBiographySearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonBackForBiography.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.buttonBiography.setOnClickListener {
            if (binding.etBiography.text.isNotEmpty()) {
                hideKeyBoard()
                responseServerBiographyArtist()
            }
        }

    }

    private fun responseServerBiographyArtist() {
        ManagerService.getServiceLastFmApi()
            .getBiographyArtist(binding.etBiography.text.toString())
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

                        Glide.with(this@BiographyFragment)
                            .load(artist.images[1].url)
                            .placeholder(R.color.violet)
                            .fitCenter()
                            .into(binding.pictureArtist)

                        binding.nameArtist.text = artist.name
                        binding.bioArtist.text = artist.bio.summary
                    }
                }

                override fun onFailure(call: Call<ArtistResponse>, t: Throwable) {
                    Toast.makeText(context, "Проверьте подключение к интернету", Toast.LENGTH_SHORT)
                        .show()
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