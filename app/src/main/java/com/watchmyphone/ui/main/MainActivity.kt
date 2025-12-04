package com.watchmyphone.ui.main

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.commit
import com.watchmyphone.R
import com.watchmyphone.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContentView(binding.root)

        val insetsController = WindowInsetsControllerCompat(window, window.decorView)
        insetsController.isAppearanceLightStatusBars = false // white status bar icons
        binding.root.setBackgroundColor(ContextCompat.getColor(this, android.R.color.black))  // Draw your own black background behind the status bar area

        // check usage access - simple open settings flow
/*        binding.rootView.setOnClickListener {
            startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
        }*/

        // Load fragment only once when the activity starts
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.container, com.watchmyphone.ui.intruder.IntruderListFragment())
            }
        }
    }
}
