package com.example.apilist.data.network

import com.example.apilist.data.model.Data
import okhttp3.OkHttpClient
import retrofit2.Response // ojito response retrofit  hay otro

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {
        @GET("character/")
        suspend fun getData(): Response<Data>                                                                //Això és necessari ja que la crida la farem dins d’una corrutina.


    companion object {
            val BASE_URL = "https://api.disneyapi.dev"
            fun create(): ApiInterface {
                val client = OkHttpClient.Builder().build()
                val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
                    GsonConverterFactory.create()
                ).client(client).build()
                return retrofit.create(ApiInterface::class.java)
            }
        }
    }

