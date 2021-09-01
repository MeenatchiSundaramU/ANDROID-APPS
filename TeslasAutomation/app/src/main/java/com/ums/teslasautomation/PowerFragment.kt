package com.ums.teslasautomation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_power.*
import kotlinx.android.synthetic.main.fragment_power.view.*
import java.text.SimpleDateFormat
import java.util.*

class PowerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_power, container, false)
        retrievedata()
        view.cv_record.setOnClickListener {
            save_data()
        }
        return view
    }

    @SuppressLint("SimpleDateFormat")
    private fun save_data() {
        val vol_get=voltage_1.text.toString()
        val i_get=current_1.text.toString()
        val calendar=Calendar.getInstance()
        val date=SimpleDateFormat("dd-MM-y").format(calendar.time)
        val time = SimpleDateFormat("hh:mm:ss a").format(calendar.time)
        val save_ref=FirebaseDatabase.getInstance().getReference("/V_I_LOG/$date").push()
        save_ref.setValue(save_power(vol_get,i_get,time))
    }

    private fun retrievedata()
    {
        val v_ref=FirebaseDatabase.getInstance().getReference("/Voltage")
        val i_ref=FirebaseDatabase.getInstance().getReference("/Current")
        v_ref.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                voltage_1.text=p0.value.toString()
            }

        })
        i_ref.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
               current_1.text=p0.value.toString()
            }

        })
    }
    class save_power(val voltage:String,val current:String,val time:String)
    {
        constructor() : this("","","")
    }
}