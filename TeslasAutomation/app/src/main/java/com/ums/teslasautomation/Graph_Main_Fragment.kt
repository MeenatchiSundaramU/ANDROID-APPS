package com.ums.teslasautomation

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import kotlinx.android.synthetic.main.fragment_graph__main_.*
import kotlinx.android.synthetic.main.fragment_graph__main_.view.*

class Graph_Main_Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_graph__main_, container, false)
        view.tempvisual_1.setOnClickListener {
            getDate("t")

        }
       view.vvisual_1.setOnClickListener {
            getDate("v")
        }
        return view
    }
    private fun getDate(getstr:String)
    {
        val alert=AlertDialog.Builder(context)
        alert.setTitle("Date Required")
        val layout=LayoutInflater.from(context)
        val access_view=layout.inflate(R.layout.alert_view,null)
        val access_date=access_view.findViewById<EditText>(R.id.visual_date)
        alert.setView(access_view)
        alert.setPositiveButton("SEARCH"){_,_ ->
               val getdate=access_date.text.toString()
            if(getstr=="t")
                {
                    val ind=Intent(context,GraphActivity::class.java)
                   ind.putExtra("VISUAL_VALUE1",getstr)
                    ind.putExtra("VISUAL_VALUE2",getdate)
                    startActivity(ind)
                }
            else{
               val ind=Intent(context,Graph_Voltage_Activity2::class.java)
                ind.putExtra("VISUAL_VALUE1",getstr)
                ind.putExtra("VISUAL_VALUE2",getdate)
                startActivity(ind)
            }


        }
        alert.setNegativeButton("CANCEL"){_,_ ->

        }
        alert.create()
        alert.show()
    }
}