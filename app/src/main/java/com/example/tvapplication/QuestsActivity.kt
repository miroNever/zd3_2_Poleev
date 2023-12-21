package com.example.tvapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.example.tvapplication.databinding.ActivityQuestsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class QuestsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuestsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.news -> {
                    loadFragment(NewsFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.detective -> {
                    val selectedValue = "detective"
                    val fragment = MoviesByGenreFragment.newInstance(selectedValue)
                    loadFragment(fragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.comedy -> {
                    val selectedValue = "comedy"
                    val fragment = MoviesByGenreFragment.newInstance(selectedValue)
                    loadFragment(fragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.horror -> {
                    val selectedValue = "horror"
                    val fragment = MoviesByGenreFragment.newInstance(selectedValue)
                    loadFragment(fragment)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }

        // По умолчанию загружаем фрагмент "Главная"
        loadFragment(NewsFragment())
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}
