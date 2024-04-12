package com.example.myhotel.data.repository

import com.example.myhotel.data.network.HotelApi

class HotelRepository (
    private val api: HotelApi
):SafeApiRequest(){
 suspend fun getHotel()=apiRequest { api.getHotels() }
}

