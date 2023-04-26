package com.moaimar.ricknmortyapp.app.data.local.cache

import android.content.SharedPreferences
import com.moaimar.ricknmortyapp.app.di.CacheQualifier
import javax.inject.Inject

const val LOCATIONS_CACHE_KEY = "locations_cache"
const val CHARACTERS_CACHE_KEY = "character_cache"

class AppCache @Inject constructor(
    @CacheQualifier private val preferences: SharedPreferences
) {

    private val editor = preferences.edit()

    fun isCacheOutDated(cacheKey: String): Boolean {
        val timeSave = preferences.getLong(cacheKey, 1)
        return if (timeSave.compareTo(1) == 0) {
            true
        } else {
            val timeLimit = 1000 * 3600 * 2//horas
            val timePassed = (System.currentTimeMillis() - timeSave)
            timePassed > timeLimit
        }
    }

    fun saveCacheDate(cacheKey: String) {
        editor.putLong(cacheKey, System.currentTimeMillis())
        editor.apply()
    }

    fun refreshCache(cacheKey: String) {
        editor.remove(cacheKey)
        editor.apply()
    }
}