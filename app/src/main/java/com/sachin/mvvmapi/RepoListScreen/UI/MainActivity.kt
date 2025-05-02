package com.sachin.mvvmapi.RepoListScreen.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.sachin.mvvm2.CatList.ViewModel.HomeViewModel
import com.sachin.mvvmapi.R
import com.sachin.mvvmapi.RepoDetailScreen.RepoDetailActivity
import com.sachin.mvvmapi.RepoListScreen.model.RepoResponseModelItem
import com.sachin.mvvmapi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job


@AndroidEntryPoint
class MainActivity : AppCompatActivity() ,onClick{

    lateinit var binding: ActivityMainBinding

    val homeViewModel: HomeViewModel by viewModels()  // ViewModel injection via Hilt


    lateinit var repolist: ArrayList<RepoResponseModelItem>

    lateinit var repolistAdapter: RepolistAdapter
    private var searchJob: Job? = null

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left +20 , systemBars.top+30, systemBars.right, 0)
            insets
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavView.setupWithNavController(navController)





//        val user = intent.getStringExtra("USER")
//        val name = intent.getStringExtra("NAME")
//        val email = intent.getStringExtra("EMAIL")
//        val photo = intent.getStringExtra("PROFILE")


        // Use the retrieved data
  //      Log.d("MainActivity", "User: $user, Name: $name, Email: $email")




        //setup RV

        repolist = ArrayList(emptyList())
//        binding.catImageRV.layoutManager = LinearLayoutManager(this)
//        repolistAdapter = RepolistAdapter(repolist, this)
//        binding.catImageRV.adapter = repolistAdapter


/*
        repoListViewModel.repoListLiveData.observe(this, Observer { result ->
            when (result) {
                is Resource.Loading -> {

                    binding.progressCircular.visibility = View.VISIBLE
                }

                is Resource.Success -> {

                    binding.progressCircular.visibility = View.GONE

                    repolist.clear()
                    var data = result.data
                    repolist.addAll(data)
                    Log.d("repodata", data.toString())
                    repolistAdapter.notifyDataSetChanged()

                }

                is Resource.Error -> {
                    binding.progressCircular.visibility = View.GONE
                    //toast
                    Toast.makeText(this@MainActivity,"Something Error Happend",Toast.LENGTH_SHORT).show()


                }
            }
        })
*/

        // API
     //   homeViewModel.getRepoListAPIData("ytsaxena")
//
//        binding.settingsbtn.setOnClickListener {
//
//            val intent = Intent(this@MainActivity,SettingsActivity::class.java)
//            startActivity(intent)
//
//
//        }
//
//
//        binding.searchbtn.setOnClickListener {
//
//            val query = binding.searchedt.text.toString()
//            repoListViewModel.getRepoListAPIData(query)
//
//            searchJob?.cancel()
//
//            searchJob = lifecycleScope.launch {
//                delay(500) // Debounce delay, adjust as needed
//                if (query.isNotBlank()) {
//                    repoListViewModel.getRepoListAPIData(query)
//                }
//            }
//
//            binding.progressCircular.visibility =View.VISIBLE
//        }

    }





    override fun onClickListItem(
        pos: Int?,
        name: String?,
        description: String?,
        owner: String?,
        language: String?,
        stargazersCount: Int?
    ) {
        val intent = Intent(this@MainActivity,RepoDetailActivity::class.java)
        intent.putExtra("NAME",name);
        intent.putExtra("DESC",description);
        intent.putExtra("OWNER",owner);
        intent.putExtra("LANG",language);
        intent.putExtra("STAR",stargazersCount);
        startActivity(intent)
    }
}


