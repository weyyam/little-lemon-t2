package com.example.littlelemon

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {
    private const val PREF_NAME = "Onboarding_preferences"

    fun getSharedPreferences(context: Context): SharedPreferences{
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun getFirstName(context: Context): String{
        return getSharedPreferences(context).getString("firstname", "") ?: ""
    }

    fun getLastName(context: Context): String{
        return getSharedPreferences(context).getString("lastname", "") ?: ""
    }

    fun getEmailAddress(context: Context): String{
        return getSharedPreferences(context).getString("emailaddress", "") ?: ""
    }
}