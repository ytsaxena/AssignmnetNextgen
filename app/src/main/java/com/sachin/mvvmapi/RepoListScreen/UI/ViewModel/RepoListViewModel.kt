package com.sachin.mvvm2.CatList.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sachin.mvvm2.CatList.Repository.HomeRepository
import com.sachin.mvvm2.Utility.Resource
import com.sachin.mvvmapi.RepoListScreen.model.RepoResponseModelItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RepoListViewModel @Inject constructor(var repoListRepository: HomeRepository) : ViewModel() {

    private var _repoListMutableLiveData = MutableLiveData<Resource<List<RepoResponseModelItem>>>()
    var repoListLiveData = _repoListMutableLiveData as LiveData<Resource<List<RepoResponseModelItem>>>

/*
    fun getRepoListAPIData(username: String) {

        viewModelScope.launch(Dispatchers.IO) {

//            val apiresponse = catListRepository.getCatListAPI(limit)
//            _catImageListMutableLiveData.postValue(apiresponse)


                val apiresponse = repoListRepository.getRepoListAPI(username)
                _repoListMutableLiveData.postValue(apiresponse)


        }
    }
*/



}
