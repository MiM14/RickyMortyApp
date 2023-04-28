package com.moaimar.ricknmortyapp.app.data.local.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.moaimar.ricknmortyapp.features.locations.domain.Resident

class ListOfStringsConverter {

    @TypeConverter
    fun listToJson(residents : List<Resident>): String = Gson().toJson(residents)

    @TypeConverter
    fun jsonToList(residents: String): List<Resident> =
        Gson().fromJson(residents, Array<Resident>::class.java).toList()
}