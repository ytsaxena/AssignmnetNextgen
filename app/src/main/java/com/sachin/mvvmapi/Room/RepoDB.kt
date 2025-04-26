package com.sachin.mvvmapi.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sachin.mvvmapi.RepoListScreen.model.RepoResponseModelItem


@Database(entities = [RepoResponseModelItem::class], version = 4)
abstract class RepoDB : RoomDatabase() {
    abstract fun repoDao(): RepoDAO
}