package com.example.myhotel.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.myhotel.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ReservationFragment : Fragment() {

    @SuppressLint("CheckResult", "MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.reservation_id_screen, container, false)
        val messageTextView: TextView = view.findViewById(R.id.messageTextView)
        val gif : ImageView = view.findViewById(R.id.gify)

        // Load GIF using Glide
        Glide.with(this)
            .asGif()
            .load(R.drawable.refund)
            .into(gif) // Specify the target view



        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
