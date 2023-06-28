package com.example.resecondsense_v01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class AnalyticsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_analytics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val barChart = view.findViewById<com.github.mikephil.charting.charts.BarChart>(R.id.catBarChart)

        val list: ArrayList<com.github.mikephil.charting.data.BarEntry> = ArrayList()
        list.add(com.github.mikephil.charting.data.BarEntry(100f, 10f))
        list.add(com.github.mikephil.charting.data.BarEntry(101f, 20f))
        list.add(com.github.mikephil.charting.data.BarEntry(102f, 30f))
        list.add(com.github.mikephil.charting.data.BarEntry(103f, 40f))
        list.add(com.github.mikephil.charting.data.BarEntry(104f, 50f))

        val barDataSet = com.github.mikephil.charting.data.BarDataSet(list, "Cats")
        barDataSet.setColors(com.github.mikephil.charting.utils.ColorTemplate.MATERIAL_COLORS, 255)
        barDataSet.valueTextColor = android.graphics.Color.BLACK

        val barData = com.github.mikephil.charting.data.BarData(barDataSet)
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
