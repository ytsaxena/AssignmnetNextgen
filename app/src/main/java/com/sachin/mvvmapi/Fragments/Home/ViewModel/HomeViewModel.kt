package com.sachin.mvvm2.CatList.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.common.cache.AbstractCache.StatsCounter
import com.sachin.mvvm2.CatList.Repository.HomeRepository
import com.sachin.mvvm2.Utility.Resource
import com.sachin.mvvmapi.Fragments.Home.model.Article
import com.sachin.mvvmapi.Fragments.Home.model.NewsResponseModel
import com.sachin.mvvmapi.RepoListScreen.model.RepoResponseModelItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(var homeRepository: HomeRepository) : ViewModel() {

    private var _newsListMutableLiveData = MutableLiveData<Resource<NewsResponseModel>>()
    var newsListLiveData = _newsListMutableLiveData as LiveData<Resource<NewsResponseModel>>

    fun getNewsListAPIData(country: String) {

        viewModelScope.launch(Dispatchers.IO) {

//            val apiresponse = catListRepository.getCatListAPI(limit)
//            _catImageListMutableLiveData.postValue(apiresponse)


                val apiresponse = homeRepository.getNewsAPI(country)
                _newsListMutableLiveData.postValue(apiresponse)


        }
    }



}
