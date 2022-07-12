package com.example.myapp.services

import com.example.myapp.dto.BeersResponse
import com.example.myapp.model.Beer
import retrofit2.Response
import retrofit2.http.GET

interface IAPIServices {
    @GET("v2/beers")
    suspend fun getBeers(): Response<ArrayList<Beer>>
}