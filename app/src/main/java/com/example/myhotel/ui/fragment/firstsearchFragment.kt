import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myhotel.R
import com.example.myhotel.ui.activity.MainActivity
import java.util.*

class firstsearchFragment : Fragment() {

    private lateinit var startDateButton: Button
    private lateinit var endDateButton: Button
    private lateinit var userName: EditText
    private lateinit var guestNumber: EditText
    private lateinit var searchButton: Button
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var check_in_date: Button
    private lateinit var check_out_date: Button


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize start date and end date buttons
        startDateButton = view.findViewById(R.id.startdate)
        endDateButton = view.findViewById(R.id.enddate)
        searchButton = view.findViewById(R.id.btn_search)
        userName = view.findViewById(R.id.edit_name)
        guestNumber = view.findViewById(R.id.edit_guests)
        check_in_date = view.findViewById(R.id.startdate)
        check_out_date = view.findViewById(R.id.enddate)

        // Initialize SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("MyHotelPrefs", Context.MODE_PRIVATE)

        // Retrieve user name and guest number from SharedPreferences
        userName.setText(sharedPreferences.getString("userName", ""))
        guestNumber.setText(sharedPreferences.getString("guestNumber", ""))
        check_in_date.setText(sharedPreferences.getString("check in date", ""))
       check_out_date.setText(sharedPreferences.getString("check out date", ""))

        // Set OnClickListener for start date button
        startDateButton.setOnClickListener {
            showDatePicker(startDateButton)
        }

        // Set OnClickListener for end date button
        endDateButton.setOnClickListener {
            showDatePicker(endDateButton)
        }

        searchButton.setOnClickListener {

            // Save user name and guest number to SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("userName", userName.text.toString())
            editor.putString("guestNumber", guestNumber.text.toString())
            editor.putString("check in date", check_in_date.text.toString())
            editor.putString("check out date", check_out_date.text.toString())
            editor.apply()

            // Show Toast message indicating that data is saved
            Toast.makeText(requireContext(), "User name and guest number saved!", Toast.LENGTH_SHORT).show()
            (requireActivity() as? MainActivity)?.replaceFragment(HotelFragment())

        }
    }

    @SuppressLint("SetTextI18n")
    private fun showDatePicker(button: Button) {
        // Get current date
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new DatePickerDialog instance
        val datePickerDialog = DatePickerDialog(requireActivity(),
            { _, year, monthOfYear, dayOfMonth ->
                // Set selected date to the button text
                button.text = "$dayOfMonth/${monthOfYear + 1}/$year"
            }, year, month, day)

        // Show the DatePickerDialog
        datePickerDialog.show()
    }
}
