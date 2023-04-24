package com.moaimar.ricknmortyapp.app.data.local.db

import androidx.room.TypeConverter
import com.google.gson.Gson

class ListOfStringsConverter {

    @TypeConverter
    fun listToJson(images: List<String>): String = Gson().toJson(images)

    @TypeConverter
    fun jsonToList(images: String): List<String> =
        Gson().fromJson(images, Array<String>::class.java).toList()
}