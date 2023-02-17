package com.example.androiddevelopercourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androiddevelopercourse.databinding.ActivityMainBinding
import com.example.androiddevelopercourse.fragments.FilmFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.holder, FilmFragment()).commit()

    }
}