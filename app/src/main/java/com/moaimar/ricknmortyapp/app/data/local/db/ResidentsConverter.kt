package com.moaimar.ricknmortyapp.app.data.local.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.moaimar.ricknmortyapp.features.locations.domain.Resident

class ResidentsConverter {

    @TypeConverter
    fun toJson(residents: List<Resident>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Resident>>() {}.type
        return gson.toJson(residents, type)
    }

    @TypeConverter
    fun fromJson(residents: String): List<Resident> {
        val gson = Gson()
        val type = object : TypeToken<List<Resident>>() {}.type
        return gson.fromJson(residents, type)
    }
}