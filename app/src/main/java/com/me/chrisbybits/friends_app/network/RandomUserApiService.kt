package com.me.chrisbybits.friends_app.network

import com.me.chrisbybits.friends_app.communication.ApiResponse
import com.me.chrisbybits.friends_app.constants.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApiService {
    // We use Retrofit to make a GET request to the Random User API
    @GET(Constants.RESOURCE_PATH)
    fun getUsers(@Query("results") results: Int): Call<ApiResponse>
}