package com.sachin.mvvmapi.Room

import androidx.room.TypeConverter
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.sachin.mvvmapi.RepoListScreen.model.License
import com.sachin.mvvmapi.RepoListScreen.model.Owner

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromLicense(value: License?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toLicense(value: String?): License? {
        return value?.let { gson.fromJson(it, License::class.java) }
    }

    @TypeConverter
    fun fromOwner(value: Owner?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toOwner(value: String?): Owner? {
        return value?.let { gson.fromJson(it, Owner::class.java) }
    }
}

