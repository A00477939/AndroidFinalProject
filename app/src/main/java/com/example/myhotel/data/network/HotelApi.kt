package com.example.myhotel.data.network

import com.example.myhotel.data.model.Hotel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("hotel")
    suspend fun getHotels() : Response<List<Hotel>> // Change return type to Response<List<Hotel>>

    companion object {
        private var apiService: ApiService? = null

        fun getInstance() : ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("http://192.168.50.24:8000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}
