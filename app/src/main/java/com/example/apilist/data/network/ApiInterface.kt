package com.example.apilist.data.network

import android.provider.ContactsContract
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/*class ApiInterface {
    @GET("people/")

    suspend fun getData(): Response<Data> {
    }

    companion object {
        val BASE_URL = "https://swapi.dev/api/"
        fun create(): ApiInterface {
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
                GsonConverterFactory.create()
            ).client(client).build()
            return retrofit.create(ApiInterface::class.java)
        }
    }

}*/