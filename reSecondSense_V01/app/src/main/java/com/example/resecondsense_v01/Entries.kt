package com.example.resecondsense_v01

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resecondsense_v01.databinding.FragmentEntriesBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Entries.newInstance] factory method to
 * create an instance of this fragment.
 */
class Entries : Fragment() {
    //binding
    private var _binding: FragmentEntriesBinding? = null
    private lateinit var recyclerView: RecyclerView // Declare recyclerView as a class-level property
    private var data: ArrayList<data_Entries> = arrayListOf() // Declare data as a class-level property


    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        _binding = FragmentEntriesBinding.inflate(inflater, container, false)
        val view = binding.root
        val currentDate: Date = Date()
        val data = arrayListOf(
            data_Entries("Math", "2 hrs", "2023-05-10", "User1", ""),
            data_Entries("English", "4 hrs", "2023-05-15", "User2", ""),
            data_Entries("Science", "1 hrs", "2023-05-17", "User1", ""),
            data_Entries("Biology", "3 hrs", "2023-05-22", "User1", ""),
            data_Entries("Social Studius", "4 hrs", "2023-05-25", "User2", "")

            // Add more items as needed
        )

        recyclerView = view.findViewById(R.id.lvEntries) // Initialize recyclerView


        //establishing the view that will display the different categories
        val recyclerView: RecyclerView = view.findViewById(R.id.lvEntries)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = RVAdapter_Entries(data)

        val CreateEntybtnClick = view.findViewById<Button>(R.id.btnCreateEntry)
        CreateEntybtnClick.setOnClickListener {

            // Create an Intent to navigate to the target activity
            val intent = Intent(requireContext(), AddNewEntries::class.java)

            // Optionally, add extras to the Intent

            // Optionally, add extras to the Intent
            intent.putExtra("key", "value")

            // Start the activity

            // Start the activity
            startActivity(intent)
        }

        val showCustomPopup: Button = view.findViewById(R.id.btnCustom)
        showCustomPopup.setOnClickListener {
            showPopupDialog()
        }


        return view
    }
    private fun showPopupDialog() {
        val popupView = layoutInflater.inflate(R.layout.popup_date_range, null)
        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(popupView)
        val dialog = dialogBuilder.create()

        val enterStartDate: EditText = popupView.findViewById(R.id.EnterStDate)
        val enterEndDate: EditText = popupView.findViewById(R.id.EnterEdDate2)
        val cancelButton: Button = popupView.findViewById(R.id.CanButton)
        val doneButton: Button = popupView.findViewById(R.id.DnButton)

        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        doneButton.setOnClickListener {
            val startDate = enterStartDate.text.toString()
            val endDate = enterEndDate.text.toString()
            filterData(startDate, endDate)
            dialog.dismiss()
        }

        dialog.show()
    }


    private fun filterData(startDate: String, endDate: String) {
        val startDateObj = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(startDate)
        val endDateObj = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(endDate)

        val filteredData = data.filter { entry ->
            val entryDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(entry.entryDate)
            entryDate in startDateObj..endDateObj
        }
        val filteredArrayList = ArrayList(filteredData) // Convert the filtered list to an ArrayList
        recyclerView.adapter = RVAdapter_Entries(filteredArrayList)

        // Show a toast message to indicate the data has been filtered
        Toast.makeText(requireContext(), "Data filtered successfully", Toast.LENGTH_SHORT).show()
    }







}