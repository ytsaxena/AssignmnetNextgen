package com.sachin.mvvmapi.Utility

import android.content.Context

class SessionManager(context: Context) {

    private val prefs = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    fun saveUserData(uid: String, name: String?, email: String?, photoUrl: String?) {
        prefs.edit().apply {
            putString("USER_ID", uid)
            putString("NAME", name)
            putString("EMAIL", email)
            putString("PHOTO", photoUrl)
            apply()
        }
    }

    fun getUserId() = prefs.getString("USER_ID", null)
    fun getName() = prefs.getString("NAME", null)
    fun getEmail() = prefs.getString("EMAIL", null)
    fun getPhotoUrl() = prefs.getString("PHOTO", null)

    fun clear() {
        prefs.edit().clear().apply()
    }
}
