package com.example.myhotel.data.network

import com.example.myhotel.data.model.Hotel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface HotelApi {
    @GET("hotel")
    suspend fun getHotels(): Response<List<Hotel>>

    companion object {
        operator fun invoke(): HotelApi {
            return Retrofit.Builder()
                .baseUrl("http://192.168.50.24:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HotelApi::class.java)
        }
    }
}
