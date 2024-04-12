package com.example.myhotel.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myhotel.R
import firstsearchFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = firstsearchFragment()
        fragmentTransaction.replace(R.id.firstscreen, fragment)
        fragmentTransaction.commit()
    }
}