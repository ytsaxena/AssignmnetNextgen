package com.sachin.mvvmapi.RepoDetailScreen

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sachin.mvvmapi.R
import com.sachin.mvvmapi.databinding.ActivityRepoDetailBinding

class RepoDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityRepoDetailBinding


    var name = ""
    var description = ""
    var owner = ""
    var language = ""
    var stargazersCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // setContentView(R.layout.activity_repo_detail)


        binding = ActivityRepoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detailroot)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left +20 , systemBars.top+30, systemBars.right, systemBars.bottom)
            insets
        }

        name = intent.getStringExtra("NAME") ?: "-"
        description = intent.getStringExtra("DESC") ?: "No description"
        owner = intent.getStringExtra("OWNER") ?: "-"
        language = intent.getStringExtra("LANG") ?: "-"
        stargazersCount = intent.getIntExtra("STAR", 0)





        binding.cardText.text = name
        binding.ownerText.text = owner
        binding.descText.text = description
        binding.langText.text = language
        binding.startext.text = stargazersCount.toString()

    }


}