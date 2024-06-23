package com.practicum.applastfm.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.practicum.applastfm.R
import com.practicum.applastfm.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        val controller = findNavController()

        binding.biography.setOnClickListener {
            controller.navigate(R.id.biographyFragment)
        }

        binding.bestTracks.setOnClickListener {
            controller.navigate(R.id.bestTracksFragment)
        }

        return binding.root
    }

}