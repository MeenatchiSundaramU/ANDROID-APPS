package com.ums.teslasautomation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import kotlinx.android.synthetic.main.activity_graph.*

class Graph_Voltage_Activity2 : AppCompatActivity() {
    var valuestemp_v = arrayListOf<Float>(4.95F,4.95F,4.94F,4.94F,4.92F,4.73F,3.84F)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(valuestemp_v[0], 0))
        entries.add(BarEntry(valuestemp_v[1], 1))
        entries.add(BarEntry(valuestemp_v[2],2))
        entries.add(BarEntry(valuestemp_v[3],3))
        entries.add(BarEntry(valuestemp_v[4],4))
        entries.add(BarEntry(valuestemp_v[5],5))
        entries.add(BarEntry(valuestemp_v[6],6))

        val barDataSet = BarDataSet(entries, "Cells")

        val labels = ArrayList<String>()
        labels.add("09:18:09 AM")
        labels.add("08:32:20 PM")
        labels.add("08:33:26 PM")
        labels.add("08:32:39 PM")
        labels.add("08:32:42 PM")
        labels.add("08:32:45 PM")
        labels.add("08:32:45 PM")

        val data = BarData(labels, barDataSet)
        barCharts.data = data // set the data and list of lables into chart

        barCharts.setDescription("Set Bar Chart Description")  // set the description

        // barDataSet.setColors(ColorTemplate.COLORFUL_COLORS)
        barDataSet.color = resources.getColor(R.color.colorAccent)

        barCharts.animateY(1000)
    }
}