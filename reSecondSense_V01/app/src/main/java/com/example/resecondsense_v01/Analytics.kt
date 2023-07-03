package com.example.resecondsense_v01

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.database.*

class AnalyticsFragment : Fragment() {

    val dbhelper = DataContext

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_analytics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val barChart = view.findViewById<BarChart>(R.id.catBarChart)

        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(1f, 10f)) // First bar
        entries.add(BarEntry(2f, 15f)) // Second bar
        entries.add(BarEntry(3f, 20f)) // Third bar

        val dataSet = BarDataSet(entries, "Cats")

        val colors = ArrayList<Int>()
        colors.add(Color.RED)
        colors.add(Color.GREEN)
        colors.add(Color.BLUE)
        dataSet.colors = colors

        val barData = BarData(dataSet)
        barChart.setFitBars(true)
        barChart.data = barData
        barChart.description.text = "Bar Chart"
        barChart.animateY(2000)
    }

    companion object {
        @JvmStatic
        fun newInstance() = AnalyticsFragment()
    }
}
