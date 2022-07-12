package com.example.myapp.services

import com.example.myapp.dto.LoginRequest
import com.example.myapp.dto.LoginResponse
import retrofit2.Call
import retrofit2.http.POST

interface IAPIServices {
    interface APIService {
        @POST
        fun login(request: LoginRequest): Call<LoginResponse>
    }
}