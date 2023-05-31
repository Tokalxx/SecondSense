package com.example.resecondsense_v01

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [addNewEntry.newInstance] factory method to
 * create an instance of this fragment.
 */
class addNewEntry : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val myview : View = inflater.inflate(R.layout.fragment_add_new_entry, container, false)

        val dateEditText: TextInputEditText? = view?.findViewById(R.id.txtEntryDate)
        dateEditText?.setOnClickListener {
            showDatePickerDialog()
        }
        return myview
    }

    fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            // Handle the selected date
            val formattedDate = "$dayOfMonth/${month + 1}/$year"
            val dateEditText: TextInputEditText? = view?.findViewById(R.id.txtEntryDate)
            dateEditText?.setText(formattedDate)
        }, currentYear, currentMonth, currentDayOfMonth)

        datePicker.show()
    }

    fun showTimePickerDialog(view: View) {
        val calendar = Calendar.getInstance()
        val currentHourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        val timePicker = TimePickerDialog(
            requireContext(),
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                val selectedEditText = view as TextInputEditText
                selectedEditText.setText(selectedTime)
            },
            currentHourOfDay,
            currentMinute,
            false
        )

        timePicker.show()
    }

}