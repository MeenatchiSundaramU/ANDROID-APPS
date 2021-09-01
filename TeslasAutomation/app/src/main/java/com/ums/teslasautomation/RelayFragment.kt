package com.ums.teslasautomation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_relay.view.*

class RelayFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var action: Int? =null
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_relay, container, false)
        view.btn_1.setOnClickListener {
            if(view.btn_1.text== "RELAY 1 OFF")
            {
                Log.d("TAG","Button 1")
                view.btn_1.text="RELAY 1 ON"
                action=1
            }
            else{
                view.btn_1.text="RELAY 1 OFF"
                action=0
            }
            ActionData(action!!,"relay1")
        }
      view.btn_2.setOnClickListener {
            if(view.btn_2.text=="RELAY 2 OFF")
            {
                view.btn_2.text="RELAY 2 ON"
                action=1
            }
            else {
                view.btn_2.text = "RELAY 2 OFF"
                action = 0
            }
          ActionData(action!!,"relay2")
        }
        view.btn_3.setOnClickListener {
            Log.d("TAG","Button 3")
            if(view.btn_3.text=="RELAY 3 OFF")
            {
                view.btn_3.text="RELAY 3 ON"
                action=1
            }
            else{
                view.btn_3.text="RELAY 3 OFF"
                action=0
            }
            ActionData(action!!,"relay3")
        }
        view.btn_4.setOnClickListener {
            Log.d("TAG","Button 4")
            if(view.btn_4.text=="RELAY 4 OFF")
            {
                view.btn_4.text="RELAY 4 ON"
                action=1
            }
            else{
                view.btn_4.text="RELAY 4 OFF"
                action=0
            }
            ActionData(action!!,"relay4")
        }
        return view
    }
    private fun ActionData(a:Int,r:String)
    {
        val data_ref=FirebaseDatabase.getInstance().getReference("/Relay/$r")
        data_ref.setValue(a).addOnCompleteListener {
            if(it.isSuccessful)
            {
                Log.d("TAG","Data Saved Succesfully")
        }
            else{
                Log.d("TAG",it.exception.toString())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ActionData(0,"relay1")
        ActionData(0,"relay2")
        ActionData(0,"relay3")
        ActionData(0,"relay4")
    }
}