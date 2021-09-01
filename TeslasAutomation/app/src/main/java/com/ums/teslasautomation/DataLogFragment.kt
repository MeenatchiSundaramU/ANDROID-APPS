package com.ums.teslasautomation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_data_log.view.*

class DataLogFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_data_log, container, false)
        view.vi_1.setOnClickListener {
           startActivity(Intent(context,PowerActivity::class.java))
        }
        view.templog_1.setOnClickListener {
            startActivity(Intent(context,TempActivity::class.java))
        }
        return view
    }
}