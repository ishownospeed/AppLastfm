package com.practicum.applastfm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.practicum.applastfm.databinding.ActivityMainBinding
import com.practicum.applastfm.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            showMainMenu()
        }

    }

    private fun showMainMenu() {
        supportFragmentManager.commit {
            addToBackStack(null)
            setReorderingAllowed(true)
            add<MainFragment>(R.id.container)
        }
    }

}