package com.ums.teslasautomation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_graph.*
import java.util.*
import kotlin.collections.ArrayList
import java.util.*
class GraphActivity : AppCompatActivity() {
        var values_v = arrayListOf<Float>(32.3F,32.4F,32.5F)
       // var time = ArrayList<String>()
    val entries = ArrayList<BarEntry>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)
        var choice: String
        val gets_date = intent.getStringExtra("VISUAL_VALUE2")
        var gets_choice = intent.getStringExtra("VISUAL_VALUE1")
        if(gets_choice=="t")
        {
            choice="TemperatureRecord"
        }
        else{
            choice="V_I_LOG"
        }
      //  retrieve_data(gets_date!!,choice)
       val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(values_v[0], 0))
        entries.add(BarEntry(values_v[1], 1))
        entries.add(BarEntry(values_v[2],2))

        val barDataSet = BarDataSet(entries, "Cells")

        val labels = ArrayList<String>()
        labels.add("08:33:02 PM")
        labels.add("08:33:23 PM")
        labels.add("08:33:55 PM")
        val data = BarData(labels, barDataSet)
        barCharts.data = data // set the data and list of lables into chart

        barCharts.setDescription("Set Bar Chart Description")  // set the description

       // barDataSet.setColors(ColorTemplate.COLORFUL_COLORS)
        barDataSet.color = resources.getColor(R.color.colorAccent)

        barCharts.animateY(1000)
    }
    /*     private fun retrieve_data(date:String,choices:String)
    {
          val ref=FirebaseDatabase.getInstance().getReference("/$choices/$date")

          ref.addValueEventListener(object :ValueEventListener{
              override fun onCancelled(p0: DatabaseError) {

              }

              override fun onDataChange(p0: DataSnapshot) {
                  for(h in p0.children) {
                      if (choices == "TemperatureRecord") {
                          val gets_data = h.getValue(TempActivity.temp_save::class.java)
                          if(gets_data==null){
                              Log.d("TAGSS","Empty")
                              return
                          }
                          values_v.add(gets_data.temp.toFloat())
                          time.add(gets_data.time)
                          Log.d("TAGSS", gets_data.temp+gets_data.time)
                      }
                      else{
                          val gets_data=h.getValue(PowerActivity.power_save::class.java)
                          if(gets_data==null)
                          {
                              return
                          }
                          values_v.add(gets_data.voltage.toFloat())
                          time.add(gets_data.time)
                          Log.d("TAGSS", gets_data.voltage)
                      }
                  }
                  Log.d("TAGSS", values_v.size.toString())
                  Log.d("TAGSS", time.size.toString())
              }

          })
    }*/

    override fun onDestroy() {
        super.onDestroy()
         values_v.clear()
    }
    }