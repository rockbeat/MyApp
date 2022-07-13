package com.example.myapp.db

import androidx.room.*

@Dao
interface FavouriteDAO {
    @Query("SELECT * FROM favs WHERE idBeer == :idBeer")
    fun getBeerFavourite(idBeer: Int): FavouriteEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveFavourite(beer: FavouriteEntity)

    @Delete
    fun deleteFavourite(user: FavouriteEntity)
}