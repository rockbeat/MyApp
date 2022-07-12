package com.example.myapp.services

import android.util.Log
import com.example.myapp.dto.BeersResponse
import com.example.myapp.model.Beer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIServices {

    suspend fun getBeers(): List<Beer> {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.punkapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(IAPIServices::class.java)
        var beers = emptyList<Beer>()
        GlobalScope.launch(Dispatchers.IO) {
            val result = service.getBeers()
            if (result != null) {
                // Checking the results
                beers = result.body()!!
            }
        }

        return beers
    }
}