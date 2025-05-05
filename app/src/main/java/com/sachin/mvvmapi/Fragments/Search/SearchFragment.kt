package com.sachin.mvvmapi.Fragments.Search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sachin.mvvm2.CatList.ViewModel.HomeViewModel
import com.sachin.mvvm2.Utility.Resource
import com.sachin.mvvmapi.Fragments.Home.NewslistAdapter
import com.sachin.mvvmapi.Fragments.Home.model.Article
import com.sachin.mvvmapi.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : Fragment() {

    lateinit var binding: FragmentSearchBinding

    lateinit var newslist: ArrayList<Article>
    lateinit var newslistAdapter: SearchNewslistAdapter
    val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
//return inflater.inflate(R.layout.fragment_search, container, false)

        binding = FragmentSearchBinding.inflate(layoutInflater)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newslist = ArrayList<Article>(emptyList())
        newslistAdapter = SearchNewslistAdapter(newslist)
        binding.newsRV.layoutManager = LinearLayoutManager(activity)
        binding.newsRV.adapter = newslistAdapter


        homeViewModel.getNewsListAPIData("us","")

        binding.searchedt.text.clear()

        binding.searchbtn.setOnClickListener {

            val edtquery = binding.searchedt.text.toString()
            homeViewModel.getNewsListAPIData("us",edtquery)
            binding.progressCircular.visibility =View.VISIBLE

        }


        homeViewModel.newsListLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {
                     binding.progressCircular.visibility =View.VISIBLE

                }

                is Resource.Error -> {
                    binding.progressCircular.visibility = View.GONE

                }

                is Resource.Success -> {

                    binding.progressCircular.visibility = View.GONE

                    newslist.clear()
                    newslist.addAll(result.data.articles)
if (newslist.isEmpty())
{
    Toast.makeText(activity, "No result found.", Toast.LENGTH_SHORT).show()

}
                    newslistAdapter.notifyDataSetChanged()

                }
            }

        }


    }

}


