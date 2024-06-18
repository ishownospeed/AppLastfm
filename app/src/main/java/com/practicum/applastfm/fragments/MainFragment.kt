package com.practicum.applastfm.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.practicum.applastfm.R
import com.practicum.applastfm.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.biography.setOnClickListener {
            parentFragmentManager.commit {
                addToBackStack(null)
                hide(this@MainFragment)
                setReorderingAllowed(true)
                add<BiographyFragment>(R.id.container)
            }
        }

        binding.bestTracks.setOnClickListener {
            parentFragmentManager.commit {
                addToBackStack(null)
                hide(this@MainFragment)
                setReorderingAllowed(true)
                add<BestTracksFragment>(R.id.container)
            }
        }
    }

}