package com.example.myhotel.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myhotel.R
import com.example.myhotel.data.network.HotelApi
import com.example.myhotel.data.repository.HotelRepository
import firstsearchFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = HotelRepository(HotelApi())
        GlobalScope.launch(Dispatchers.Main) {
            val hotel =repository.getHotel()
            Toast.makeText(this@MainActivity,hotel.toString(),Toast.LENGTH_LONG).show()
        }

//        val fragmentTransaction = supportFragmentManager.beginTransaction()
//        val fragment = firstsearchFragment()
//        fragmentTransaction.replace(R.id.firstscreen, fragment)
//        fragmentTransaction.commit()
    }
}