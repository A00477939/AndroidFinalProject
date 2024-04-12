package com.example.myhotel.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myhotel.R
import com.example.myhotel.data.model.Guest
import com.example.myhotel.data.model.Hotel

class GuestDetailFragment :Fragment() {
    private lateinit var sharedPreferences: SharedPreferences
    val guestList = ArrayList<Guest>()
    private lateinit var searchButton: Button
    private lateinit var layoutGuests: LinearLayout


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.guest, container, false)
        // Initialize SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("MyHotelPrefs", Context.MODE_PRIVATE)

        // Retrieve data from SharedPreferences
        val userName = sharedPreferences.getString("userName", "")
        val guestNumber = sharedPreferences.getString("guestNumber", "")
        val guest = guestNumber?.toIntOrNull() ?: 0
        val checkInDate = sharedPreferences.getString("check in date", "")
        val checkOutDate = sharedPreferences.getString("check out date", "")
        val clickedHotel = arguments?.getParcelable<Hotel>("clickedHotel")
        if (clickedHotel != null) {
            view.findViewById<TextView>(R.id.hotel_name).text = clickedHotel.name
            view.findViewById<TextView>(R.id.textCheckIn).text = checkInDate
            view.findViewById<TextView>(R.id.textCheckOut).text = checkOutDate
            view.findViewById<TextView>(R.id.textPrice).text = clickedHotel.price
        }

        // Add guest rows dynamically
        layoutGuests = view.findViewById<LinearLayout>(R.id.layoutGuests)
        for (i in 1..guest) {
            val guestRow = inflater.inflate(R.layout.guest_row, null)
            // Set guest name hint dynamically
            guestRow.findViewById<EditText>(R.id.editTextGuestName).hint = "Guest $i Name"
            layoutGuests.addView(guestRow)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchButton = view.findViewById(R.id.buttonSave)


        searchButton.setOnClickListener {
            guestList.clear()

            for (i in 0 until layoutGuests.childCount) {
                val guestRow = layoutGuests.getChildAt(i)

                val guestName = guestRow.findViewById<EditText>(R.id.editTextGuestName).text.toString()

                val genderRadioGroup = guestRow.findViewById<RadioGroup>(R.id.radioGroupGender)
                val selectedGenderRadioButtonId = genderRadioGroup.checkedRadioButtonId
                val selectedGenderRadioButton = genderRadioGroup.findViewById<RadioButton>(selectedGenderRadioButtonId)
                val gender = selectedGenderRadioButton?.text.toString() ?: ""

                // Create a Guest object and add it to the guest list
                val guest = Guest(guestName, gender)
                guestList.add(guest)
            }

        }

    }
}
