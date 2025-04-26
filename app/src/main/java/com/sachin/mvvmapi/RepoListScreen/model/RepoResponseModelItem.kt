package com.sachin.mvvmapi.RepoListScreen.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.sachin.mvvmapi.Room.Converters
import org.intellij.lang.annotations.Language

@Entity(tableName = "repos")
@TypeConverters(Converters::class)
data class RepoResponseModelItem(


    val description: String?,

    val full_name: String?,
    @PrimaryKey val id: Int,

    val license: License?,
    val name: String?,
    val owner: Owner?,
    val stargazers_count: Int?,
    val language: String?


    )