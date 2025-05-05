package com.sachin.mvvmapi.Fragments.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.ViewPager2
import com.sachin.mvvm2.CatList.ViewModel.HomeViewModel
import com.sachin.mvvm2.Utility.Resource
import com.sachin.mvvmapi.Fragments.Home.model.Article
import com.sachin.mvvmapi.R
import com.sachin.mvvmapi.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    lateinit var newslist: ArrayList<Article>
    lateinit var newslistAdapter: NewslistAdapter
    val homeViewModel: HomeViewModel by viewModels()

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false)

        binding = FragmentHomeBinding.inflate(layoutInflater)

        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //  binding.newsRV.layoutManager = LinearLayoutManager(activity)
        // newslistAdapter = NewslistAdapter(newslist)
        //binding.newsRV.adapter = newslistAdapter

        swipeRefreshLayout = binding.root.findViewById(R.id.swipeRefreshLayout)
        newslist = ArrayList<Article>(emptyList())
        newslistAdapter = NewslistAdapter(newslist)
        binding.newsViewPager.apply {
            adapter = newslistAdapter
            orientation = ViewPager2.ORIENTATION_VERTICAL


            homeViewModel.getNewsListAPIData("us","")

            homeViewModel.newsListLiveData.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is Resource.Loading -> {
                        // binding.progressCircular.visibility =View.GONE
                        swipeRefreshLayout.isRefreshing = true
                    }

                    is Resource.Error -> {
                        binding.progressCircular.visibility = View.GONE
                        swipeRefreshLayout.isRefreshing = false
                    }

                    is Resource.Success -> {

                        binding.progressCircular.visibility = View.GONE

                        newslist.clear()
                        newslist.addAll(result.data.articles)
                        newslistAdapter.notifyDataSetChanged()

                        swipeRefreshLayout.isRefreshing = false
                    }
                }

            }

            swipeRefreshLayout.setOnRefreshListener {
                homeViewModel.getNewsListAPIData("us","")
            }

        }

    }
}



