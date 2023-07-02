package com.example.resecondsense_v01

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resecondsense_v01.databinding.FragmentEntriesBinding
import kotlinx.coroutines.launch
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
class Entries : Fragment(), RVAdapter_Entries.OnItemClickListener {
    //binding
    val dbhelper = DataContext
    private lateinit var recyclerViewAdapter: RVAdapter_Entries
    private var _binding: FragmentEntriesBinding? = null
    private lateinit var recyclerView: RecyclerView // Declare recyclerView as a class-level property
    private lateinit var data: List<data_Entries> // Declare data as a class-level property
    private lateinit var TotalHours : TextView
    private var mYear = 0
    private  var mMonth:kotlin.Int = 0
    private  var mDay:kotlin.Int = 0
    private  var mHour:kotlin.Int = 0
    private  var mMinute:kotlin.Int = 0
    private lateinit var searchView: SearchView


    private val binding get() = _binding!!
    override  fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //binding
        _binding = FragmentEntriesBinding.inflate(inflater, container, false)
        val view = binding.root

        TotalHours =view.findViewById(R.id.txtTotal)
        val btnCreateEntry = view.findViewById<Button>(R.id.btnCreateEntry)

        val btnAll : Button = view.findViewById(R.id.btnViewAll)
        lifecycleScope.launch {
            // Call suspend function here
             loadLists()
            // Use result here
        }
        data = dbhelper.getEntries()

        TotalHours.setText("Total: "+dbhelper.run{ calavulateent().toString()})
        //getting the list of entries
        recyclerView = view.findViewById(R.id.lvEntries) // Initialize recyclerView
        searchView = binding.entrySerachView //Initialize searchView
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        //establishing the view that will display the different categories
        recyclerViewAdapter = RVAdapter_Entries(data)
        recyclerViewAdapter.itemClickListener = this

        recyclerView.adapter = recyclerViewAdapter

        //search for an entry with a specific name
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                filterList(p0)
                return true
            }

        })


        //Create button, directs to the create Entry screen
        btnCreateEntry.setOnClickListener {

            // Create an Intent to navigate to the target activity
            val intent = Intent(requireContext(), AddNewEntries::class.java)

            // Optionally, add extras to the Intent
            intent.putExtra("DATA_ENTRIES", "ENTRIES")

            // Start the activity
            startActivityForResult(intent,1)
        }
        //Popup to sort filter the Entries by date
        val showCustomPopup: Button = view.findViewById(R.id.btnCustom)
        showCustomPopup.setOnClickListener {
            showPopupDialog()
        }
        //btn to view all entries
        btnAll.setOnClickListener {
            var allEntries = dbhelper.getEntries()
            recyclerViewAdapter.setFilteredList(allEntries)
        }
        return view
    }

    private fun filterList(query : String?){
        if (query != null) {
            var filteredList = ArrayList<data_Entries>()
            for (i in dbhelper.getEntries()) {
                if (i.entry_Title.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(requireContext(), "No Data found", Toast.LENGTH_SHORT).show()

            } else {
                recyclerViewAdapter.setFilteredList(filteredList)
            }
        }
    }
    suspend fun loadLists(){
        dbhelper.getTimeSheetEntryToFirestore()
        dbhelper.getImagesFromFireStore()
    }
    private fun showPopupDialog() {
        val popupView = layoutInflater.inflate(R.layout.popup_date_range, null)
        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(popupView)
        val dialog = dialogBuilder.create()

        val enterStartDate: EditText = popupView.findViewById(R.id.txtStartdate)
        val enterEndDate: EditText = popupView.findViewById(R.id.txtEnddate)
        val cancelButton: Button = popupView.findViewById(R.id.CanButton)
        val doneButton: Button = popupView.findViewById(R.id.DnButton)

        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        enterStartDate.setOnClickListener {
            val c: Calendar = Calendar.getInstance()
            mYear = c.get(Calendar.YEAR)
            mMonth = c.get(Calendar.MONTH)
            mDay = c.get(Calendar.DAY_OF_MONTH)


            val datePickerDialog = DatePickerDialog(requireContext(),
                { view, year, monthOfYear, dayOfMonth -> enterStartDate!!.setText(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year) },
                mYear,
                mMonth,
                mDay
            )
            datePickerDialog.show()
        }
        enterEndDate.setOnClickListener {
            val c: Calendar = Calendar.getInstance()
            mYear = c.get(Calendar.YEAR)
            mMonth = c.get(Calendar.MONTH)
            mDay = c.get(Calendar.DAY_OF_MONTH)


            val datePickerDialog = DatePickerDialog(requireContext(),
                { view, year, monthOfYear, dayOfMonth -> enterEndDate!!.setText(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year) },
                mYear,
                mMonth,
                mDay
            )
            datePickerDialog.show()
        }

        doneButton.setOnClickListener {
            val startDate = enterStartDate.text.toString()
            val endDate = enterEndDate.text.toString()


            filterData(startDate, endDate)
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun onResume() {
        super.onResume()

        // No need to create a new adapter here
        // Just notify the existing adapter of any data changes
        recyclerViewAdapter.notifyDataSetChanged()

    }



    private fun filterData(startDate: String, endDate: String) {
        val startDateObj = dbhelper.convertStringToDate(startDate,dbhelper.dateFormat)
        val endDateObj = dbhelper.convertStringToDate(endDate,dbhelper.dateFormat)

        val filteredArrayList = dbhelper.filterObjectsByDate(startDateObj,endDateObj,dbhelper.getEntries())// Convert the filtered list to an ArrayList
        //recyclerView.adapter = RVAdapter_Entries(filteredArrayList)
        recyclerViewAdapter.setFilteredList(filteredArrayList)
        // Show a toast message to indicate the data has been filtered
        Toast.makeText(requireContext(), "Data filtered successfully", Toast.LENGTH_SHORT).show()
    }
    //updates the recycler view when a new item is created
    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            // Check if the result code matches the one set by the other activity
            if (resultCode == Activity.RESULT_OK) {
                // Check if the intent and its extras are not null
                if (data != null && data.hasExtra("DATA_ENTRIES")) {
                    // Get the updated list of categories from the intent using the same key as before
                    val newData = data.getSerializableExtra("DATA_ENTRIES") as List<data_Entries>
                    // Pass the updated list of categories to the adapter of the RecyclerView
                    (recyclerView.adapter as? RVAdapter_Entries)?.updateData(newData)
                    TotalHours.setText(dbhelper.run{ DataContext.calavulateent().toString()})
                    // Notify the adapter that the data set has changed
                    recyclerView.adapter?.notifyDataSetChanged()
                }
            }
        }
    }

    //click event that will direct the user to see the details of an entry
    override fun onItemClick(itemId: String) {
        val intent = Intent(requireContext(), EntryDetails::class.java)
        intent.putExtra("EntryId", itemId)
        startActivityForResult(intent, 1)
    }


}