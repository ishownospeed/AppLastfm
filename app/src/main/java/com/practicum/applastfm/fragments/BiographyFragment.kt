package com.practicum.applastfm.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.practicum.applastfm.R
import com.practicum.applastfm.databinding.FragmentBiographySearchBinding
import com.practicum.viewmodel.viewmodels.BiographyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BiographyFragment : Fragment(R.layout.fragment_biography_search) {

    private lateinit var binding: FragmentBiographySearchBinding
    private val biographyViewModel: BiographyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        biographyViewModel.nameMutableLiveData.observe(this, Observer {
            binding.nameArtist.text = it
        })

        biographyViewModel.bioMutableLiveData.observe(this, Observer {
            binding.bioArtist.text = it
        })

        biographyViewModel.imageMutableLiveData.observe(this, Observer {
            Glide.with(this)
                .load(it)
                .fitCenter()
                .into(binding.pictureArtist)
        })
    }

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
            findNavController().popBackStack()
        }

        binding.buttonBiography.setOnClickListener {
            if (binding.etBiography.text.isNotEmpty()) {
                hideKeyBoard()

                biographyViewModel.responseServerBiographyArtist(
                    context = context?.applicationContext!!,
                    editText = binding.etBiography,
                    pictureArtist = binding.pictureArtist,
                    nameArtist = binding.nameArtist,
                    bioArtist = binding.bioArtist
                )
            }
        }

    }

    private fun hideKeyBoard() {
        activity?.currentFocus?.let { view ->
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}