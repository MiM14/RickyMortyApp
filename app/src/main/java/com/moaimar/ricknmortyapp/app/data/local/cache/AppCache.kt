package com.moaimar.ricknmortyapp.app.data.local.cache

import android.content.SharedPreferences
import com.moaimar.ricknmortyapp.app.di.CacheQualifier
import javax.inject.Inject

class AppCache @Inject constructor(
    @CacheQualifier private val preferences: SharedPreferences
) {

    private val editor = preferences.edit()

    fun isCacheOutDated(): Boolean {
        val timeSave = preferences.getLong("cache", 1)
        return if(timeSave.compareTo(1) == 0){
            true
        }else{
            val timeLimit = 1000 * 3600 * 2//horas
            val timePassed = (System.currentTimeMillis()-timeSave)
            timePassed > timeLimit
        }
    }

    fun saveCacheDate() {
        editor.putLong("cache",System.currentTimeMillis())
        editor.apply()
    }

    fun refreshCache(){
        editor.remove("cache")
        editor.apply()
    }
}