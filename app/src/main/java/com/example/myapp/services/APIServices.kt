package com.example.myapp.services

import android.util.Log
import com.example.myapp.dto.BeersResponse
import com.example.myapp.model.Beer
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIServices {

    suspend fun getBeers(): List<Beer> {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.punkapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return withContext(Dispatchers.IO) {
            val response = retrofit.create(IAPIServices::class.java).getBeers()
            response.body() ?: emptyList()
        }
    }
}