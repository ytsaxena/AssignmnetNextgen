package com.sachin.mvvmapi.Room

import androidx.room.TypeConverter
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.sachin.mvvmapi.RepoListScreen.model.License
import com.sachin.mvvmapi.RepoListScreen.model.Owner

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromLicense(value: License?): String = gson.toJson(value)

    @TypeConverter
    fun toLicense(value: String): License = gson.fromJson(value, License::class.java)

    @TypeConverter
    fun fromOwner(value: Owner?): String = gson.toJson(value)

    @TypeConverter
    fun toOwner(value: String): Owner = gson.fromJson(value, Owner::class.java)

    @TypeConverter
    fun fromTopics(value: List<String>?): String = gson.toJson(value)

    @TypeConverter
    fun toTopics(value: String): List<String> =
        gson.fromJson(value, object : TypeToken<List<String>>() {}.type)
}
