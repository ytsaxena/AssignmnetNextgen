package com.sachin.mvvmapi.Fragments.Search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sachin.mvvmapi.Fragments.Home.model.Article
import com.sachin.mvvmapi.RepoListScreen.UI.RepolistAdapter
import com.sachin.mvvmapi.RepoListScreen.UI.onClick
import com.sachin.mvvmapi.RepoListScreen.model.RepoResponseModelItem
import com.sachin.mvvmapi.databinding.ItemcatlistBinding
import com.sachin.mvvmapi.databinding.ItemnewslistBinding

class SearchNewslistAdapter(var repolist: ArrayList<Article>) : RecyclerView.Adapter<SearchNewslistAdapter.RepolistViewHolder>() {

        inner class RepolistViewHolder(var binding: ItemnewslistBinding) :
            RecyclerView.ViewHolder(binding.root)


        override fun onCreateViewHolder(
            parent: ViewGroup, viewType: Int
        ): SearchNewslistAdapter.RepolistViewHolder {


            return RepolistViewHolder(
                ItemnewslistBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

        }

        override fun onBindViewHolder(holder: SearchNewslistAdapter.RepolistViewHolder, position: Int) {

            val data = repolist[position]


            holder.binding.newstextheading.text = data.title.toString()
//            holder.binding.newsTextDesc.text = data?.description?.toString()
//            holder.binding.newsSource.text = data?.source?.name.toString()



            Glide.with(holder.binding.image).load(data.urlToImage).into(holder.binding.image)

                //   holder.binding.parent.setOnClickListener{

//                cardclick.onClickListItem(position ,
//                    data?.name.toString(),
//                    data?.description.toString(),
//                    data?.owner?.login,
//                    data?.language.toString(),
//                    data?.stargazers_count)





     //       }

        }

        override fun getItemCount() = repolist.size
    }
