package com.moaimar.ricknmortyapp.features.locations.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val RESIDENTS_TABLE_NAME = "residents"

@Entity(tableName = RESIDENTS_TABLE_NAME)
data class ResidentEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "image") val image: String
){
    constructor(): this(0, "")
}