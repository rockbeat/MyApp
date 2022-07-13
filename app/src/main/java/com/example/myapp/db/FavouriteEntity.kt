package com.example.myapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favs")
data class FavouriteEntity(
    @PrimaryKey(autoGenerate = false)
    val idBeer: Int = 0,
    val favourite: Boolean = false
    )