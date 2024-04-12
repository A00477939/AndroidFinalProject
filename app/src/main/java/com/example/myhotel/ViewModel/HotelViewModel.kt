package com.example.myhotel.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhotel.data.model.Hotel
import com.example.myhotel.data.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HotelViewModel : ViewModel() {

    private val apiService: ApiService = ApiService.getInstance()
    val hotels: MutableLiveData<List<Hotel>> by lazy {
        MutableLiveData<List<Hotel>>()
    }

    fun getHotels() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.getHotels()
                if (response.isSuccessful) {
                    hotels.postValue(response.body())
                }
            } catch (e: Exception) {
                // Handle error
                e.printStackTrace()
            }
        }
    }
}



//
//class HotelViewModel : ViewModel() {
//
//    val hotels: MutableLiveData<List<Hotel>> by lazy {
//        MutableLiveData<List<Hotel>>()
//    }
//
//    fun getHotels() {
//        // Generate mock data
//        val mockHotels = generateMockHotels()
//        hotels.postValue(mockHotels)
//    }
//
//    private fun generateMockHotels(): List<Hotel> {
//        val hotelList = ArrayList<Hotel>()
//        // Generate some mock hotel data
//        for (i in 1..10) {
//            val hotel = Hotel(
//                "Availability $i",
//                "ID $i",
//                "Hotel Name $i",
//                "Price $i"
//            )
//            hotelList.add(hotel)
//        }
//        return hotelList
//    }
//}
