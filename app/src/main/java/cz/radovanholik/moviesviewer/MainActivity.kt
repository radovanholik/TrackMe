package cz.radovanholik.moviesviewer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cz.radovanholik.moviesviewer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}