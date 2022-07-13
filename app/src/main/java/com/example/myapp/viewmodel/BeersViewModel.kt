package com.example.myapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapp.model.Beer
import com.example.myapp.services.APIServices

class BeersViewModel : ViewModel() {
    suspend fun getBeers(): MutableList<Beer> {

        var response = APIServices().getBeers()
        var beersList: MutableList<Beer> = ArrayList(response)
        return beersList


    }
}