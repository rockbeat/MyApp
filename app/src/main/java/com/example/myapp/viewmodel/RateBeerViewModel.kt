package com.example.myapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.myapp.db.FavouriteEntity
import com.example.myapp.helpers.Database
import com.example.myapp.model.Beer
import com.example.myapp.services.APIServices

class RateBeerViewModel : ViewModel() {
    suspend fun getBeers(context : Context): MutableList<FavouriteEntity> {

        var db: Database? = Database.getInstance(context)
        var result = db!!.getAllFavourites()
        var beers : MutableList<FavouriteEntity> = ArrayList(result)
        return beers
    }
}