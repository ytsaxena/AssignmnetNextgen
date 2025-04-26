package com.sachin.mvvmapi.DI

import android.content.Context
import androidx.room.Room
import com.sachin.mvvmapi.Room.RepoDAO
import com.sachin.mvvmapi.Room.RepoDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): RepoDB {
        return Room.databaseBuilder(
            context,
            RepoDB::class.java,
            "github_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideRepoDao(db: RepoDB): RepoDAO = db.repoDao()

}