package com.example.livedatacoroutines.data.api

import com.example.livedatacoroutines.model.User
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>

}