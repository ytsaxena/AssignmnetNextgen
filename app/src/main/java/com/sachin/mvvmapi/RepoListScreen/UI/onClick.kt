package com.sachin.mvvmapi.RepoListScreen.UI

import com.sachin.mvvmapi.RepoListScreen.model.Owner

interface onClick {

    fun onClickListItem(
        pos: Int?,
        name: String?,
        description: String?,
        owner: String?,
        language: String?,
        stargazersCount: Int?
    )
}