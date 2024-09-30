package com.me.chrisbybits.friends_app.factories


import com.me.chrisbybits.friends_app.constants.Constants
import com.me.chrisbybits.friends_app.network.RandomUserApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserServiceFactory {
    fun createRandomServiceInstance(): RandomUserApiService{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RandomUserApiService::class.java)
    }
}