package com.ums.krishedriver.Mainfragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.ums.krishedriver.Maps2Activity
import com.ums.krishedriver.MapsActivity
import com.ums.krishedriver.R
import com.ums.krishedriver.maplat
import kotlinx.android.synthetic.main.fragment_completed.*

class CompletedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_completed, container, false)
        val ctotalfareval:TextView=view.findViewById(R.id.ctotalfareval)
        val caccept:Button=view.findViewById(R.id.caccept)
        val cfarebtn:Button=view.findViewById(R.id.cfarebtn)
        cfarebtn.setOnClickListener {
            ctotalfareval.text = 300.toString()
        }
        caccept.setOnClickListener {
            val ind= Intent(context, Maps2Activity::class.java)
            val gets: maplat = maplat(8.392770, 77.589302,8.3926,77.6105)
            ind.putExtra("GET",gets)
            startActivity(ind)
        }
        return view
    }
}