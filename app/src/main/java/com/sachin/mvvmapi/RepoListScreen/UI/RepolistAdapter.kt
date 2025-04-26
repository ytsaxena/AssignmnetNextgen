package com.sachin.mvvmapi.RepoListScreen.UI

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sachin.mvvmapi.RepoListScreen.model.RepoResponseModelItem
import com.sachin.mvvmapi.databinding.ItemcatlistBinding

class RepolistAdapter(var repolist: ArrayList<RepoResponseModelItem> , var cardclick : onClick) :
    RecyclerView.Adapter<RepolistAdapter.RepolistViewHolder>() {

    inner class RepolistViewHolder(var binding: ItemcatlistBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): RepolistAdapter.RepolistViewHolder {

        //  val binding =

        return RepolistViewHolder(
            ItemcatlistBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    }

    override fun onBindViewHolder(holder: RepolistAdapter.RepolistViewHolder, position: Int) {

        val data = repolist[position]


        holder.binding.cardText.text = data.name.toString()?: ""
        holder.binding.cardTextDesc.text = data?.description?.toString()
        holder.binding.starTxt.text = data.stargazers_count.toString()?: ""



        //  Glide.with(holder.binding.cardImage).load(data.url).into(holder.binding.cardImage)


        holder.binding.parent.setOnClickListener{

            cardclick.onClickListItem(position ,
                data?.name.toString(),
                data?.description.toString(),
                data?.owner?.login,
                data?.language.toString(),
                data?.stargazers_count)





        }

    }

    override fun getItemCount() = repolist.size
}