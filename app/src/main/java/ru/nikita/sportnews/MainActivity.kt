package ru.nikita.sportnews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.nikita.sportnews.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, NewsFragment())
                .commit()
        }
    }
}
