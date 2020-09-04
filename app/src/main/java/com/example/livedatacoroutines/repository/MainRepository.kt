package com.example.livedatacoroutines.repository

import com.example.livedatacoroutines.data.api.ApiHelper


class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getUsers()
}