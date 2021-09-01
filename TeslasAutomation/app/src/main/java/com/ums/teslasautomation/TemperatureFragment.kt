package com.ums.teslasautomation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_temperature.*
import kotlinx.android.synthetic.main.fragment_temperature.view.*
import java.text.SimpleDateFormat
import java.util.*

class TemperatureFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_temperature, container, false)
        retrieve_temp()
        view.temp_record_btn.setOnClickListener {
            temp_record(view.temp_value_btn.text.toString())
        }
        return view
    }
    private fun retrieve_temp()
    {
        val ref=FirebaseDatabase.getInstance().getReference("/Temperature")
        ref.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot)
            {
                val get_temp=p0.value
                if(get_temp!=null)
                {
                    Log.d("TAG",get_temp.toString())
                    temp_value_btn.text=get_temp.toString()
                }
                else{
                    return
                }

            }
        })
    }
    private fun temp_record(temp:String)
    {
        val calendar=Calendar.getInstance()
        val date= SimpleDateFormat("dd-MM-y").format(calendar.time).toString()
        val time= SimpleDateFormat("hh:mm:ss a").format(calendar.time).toString()
        val temp_record_ref=FirebaseDatabase.getInstance().getReference("/TemperatureRecord/$date").push()
        temp_record_ref.setValue(temp_record(temp,time)).addOnCompleteListener {
            if(it.isSuccessful)
            {
                Log.d("SAVED","SAVED SUCCESS")
            }
            else{
                Log.d("SAVED",it.exception.toString())
            }
        }
    }
    class temp_record(val temp:String,val time:String)
    {
        constructor() : this("","")
    }

}