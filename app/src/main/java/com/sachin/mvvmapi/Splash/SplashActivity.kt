package com.sachin.mvvmapi.Splash

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sachin.mvvmapi.LoginScreen.LoginActivity
import com.sachin.mvvmapi.R
import com.sachin.mvvmapi.RepoListScreen.UI.MainActivity
import com.sachin.mvvmapi.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_splash)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left + 20, systemBars.top + 30, systemBars.right, systemBars.bottom
            )
            insets
        }

        val videoView = binding.videoView
        val videoUri = Uri.parse("android.resource://${packageName}/${R.raw.splash}")
        videoView.setVideoURI(videoUri)

        videoView.setOnCompletionListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        videoView.setOnPreparedListener {
            it.isLooping = false
            videoView.start()
        }





    }
}