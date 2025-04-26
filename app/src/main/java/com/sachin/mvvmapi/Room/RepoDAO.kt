package com.sachin.mvvmapi.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sachin.mvvmapi.RepoListScreen.model.RepoResponseModelItem


@Dao
interface RepoDAO {

        @Query("SELECT * FROM repos")
        suspend fun getAllRepos(): List<RepoResponseModelItem>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertRepos(repos: List<RepoResponseModelItem>)

}