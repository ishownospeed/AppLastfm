package com.practicum.applastfm.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.practicum.applastfm.R
import com.practicum.applastfm.databinding.FragmentBestTracksBinding
import com.practicum.viewmodel.viewmodels.BestTracksViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BestTracksFragment : Fragment(R.layout.fragment_best_tracks) {

    private lateinit var binding: FragmentBestTracksBinding
    private val bestTracksViewModel: BestTracksViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bestTracksViewModel.tracksMutableLiveData.observe(this) {
            binding.listBestTracks.adapter?.notifyDataSetChanged()
        }
    }

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

        binding.listBestTracks.adapter = bestTracksViewModel.adapterListTracks

        binding.buttonBackForBestTracks.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.buttonBestTracks.setOnClickListener {
            if (binding.etBestTracks.text.isNotEmpty()) {
                hideKeyBoard()

                bestTracksViewModel.responseServerTopTracksArtist(
                    context = context?.applicationContext!!,
                    editText = binding.etBestTracks
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