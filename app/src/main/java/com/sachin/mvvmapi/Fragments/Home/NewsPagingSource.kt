package com.sachin.mvvmapi.Fragments.Home

import android.graphics.pdf.LoadParams
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor
import com.sachin.mvvmapi.API.APIinterfaceService
import com.sachin.mvvmapi.Fragments.Home.model.Article
import javax.inject.Inject

//
//class NewsPagingSource @Inject constructor(
//    private val api: APIinterfaceService
//) : PagingSource<Int, Article>() {
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
//        val page = params.key ?: 1
//        return try {
//            val response = api.getNews(page = page, pageSize = 10)
//            LoadResult.Page(
//                data = response.body().,
//                prevKey = if (page == 1) null else page - 1,
//                nextKey = if (response.articles.isEmpty()) null else page + 1
//            )
//        } catch (e: Exception) {
//            LoadResult.Error(e)
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, Article>): Int? = null
//}
