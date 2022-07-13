package com.example.myapp.services

import com.example.myapp.dto.BeersResponse
import com.example.myapp.model.Beer
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface IAPIServices {
    @GET("v2/beers")
    suspend fun getBeers(): Response<ArrayList<Beer>>

    companion object {
        var retrofitService: IAPIServices? = null

        fun getInstance() : IAPIServices {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://howtodoandroid.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(IAPIServices::class.java)
            }
            return retrofitService!!
        }
    }
}