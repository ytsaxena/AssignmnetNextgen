package com.sachin.mvvmapi.Fragments.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sachin.mvvmapi.Fragments.Home.model.Article
import com.sachin.mvvmapi.RepoListScreen.UI.RepolistAdapter
import com.sachin.mvvmapi.RepoListScreen.UI.onClick
import com.sachin.mvvmapi.RepoListScreen.model.RepoResponseModelItem
import com.sachin.mvvmapi.databinding.ItemcatlistBinding

class NewslistAdapter(var repolist: ArrayList<Article>) : RecyclerView.Adapter<NewslistAdapter.RepolistViewHolder>() {

        inner class RepolistViewHolder(var binding: ItemcatlistBinding) :
            RecyclerView.ViewHolder(binding.root)


        override fun onCreateViewHolder(
            parent: ViewGroup, viewType: Int
        ): NewslistAdapter.RepolistViewHolder {


            return RepolistViewHolder(
                ItemcatlistBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

        }

        override fun onBindViewHolder(holder: NewslistAdapter.RepolistViewHolder, position: Int) {

            val data = repolist[position]


            holder.binding.newsTextHeading.text = data.title.toString()
            holder.binding.newsTextDesc.text = data?.description?.toString()
            holder.binding.newsSource.text = data?.source?.name.toString()



            Glide.with(holder.binding.cardImage).load(data.urlToImage).into(holder.binding.cardImage)

            holder.binding.parent.setOnClickListener{

//                cardclick.onClickListItem(position ,
//                    data?.name.toString(),
//                    data?.description.toString(),
//                    data?.owner?.login,
//                    data?.language.toString(),
//                    data?.stargazers_count)





            }

        }

        override fun getItemCount() = repolist.size
    }
