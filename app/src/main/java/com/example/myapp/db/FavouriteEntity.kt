package com.example.myapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapp.model.Beer

@Entity(tableName = "favs")
data class FavouriteEntity(
    @PrimaryKey(autoGenerate = false)
    val idBeer: Int = 0,
    val beer: String,
    val rate: Float = 0.0f
)