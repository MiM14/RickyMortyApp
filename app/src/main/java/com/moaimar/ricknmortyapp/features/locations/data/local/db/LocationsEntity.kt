package com.moaimar.ricknmortyapp.features.locations.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

const val LOCATIONS_TABLE_NAME = "locations"

@Entity(tableName = LOCATIONS_TABLE_NAME)
data class LocationsEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "dimension") val dimension: String,
    @Embedded(prefix = "residents_")val residents: List<ResidentEntity>
)

