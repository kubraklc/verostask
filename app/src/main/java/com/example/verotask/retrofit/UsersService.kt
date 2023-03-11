package com.example.verotask.retrofit

import android.content.Context
import com.example.verotask.network.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UsersService {
    private lateinit var usersApi: UsersApi
    val BASE_URL ="https://api.baubuddy.de/"

    fun getApiService(context: Context): UsersApi {
        if(!::usersApi.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpClient(context))
                .build()

            usersApi = retrofit.create(UsersApi::class.java)
        }

        return usersApi
    }


    private fun okhttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .build()
    }
}