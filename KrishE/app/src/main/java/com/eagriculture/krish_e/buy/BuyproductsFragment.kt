package com.eagriculture.krish_e.buy

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.eagriculture.krish_e.R


class BuyproductsFragment : Fragment() {
    lateinit var buysearchcrops: EditText

    //lateinit var buyhighqual:CheckBox
    //lateinit var buylowqual:CheckBox
    //lateinit var buymediumqual:CheckBox
    lateinit var buysearchwork: Button

    //lateinit var buyquantsearch:EditText
    lateinit var buypro: SharedPreferences

    //var hq:String="0"
    //var mq:String="0"
    //var lq:String="0"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_buyproducts, container, false)
        buypro = getContext()!!.getSharedPreferences(getString(R.string.reg_pass), MODE_PRIVATE)
        buysearchcrops = view.findViewById(R.id.buysearch)
        // buyhighqual=view.findViewById(R.id.highcategory)
        // buymediumqual=view.findViewById(R.id.mediumcategory)
        //buylowqual=view.findViewById(R.id.lowcategory)
        buysearchwork = view.findViewById(R.id.buycropsearch1)
        // buyquantsearch=view.findViewById(R.id.buysearchqauntval)
        buysearchwork.setOnClickListener {
            var getsearchcrops = buysearchcrops.text.toString()
            buypro.edit().putString("searchedcrops", getsearchcrops).apply()
            //  var searchquantity=buyquantsearch.text.toString()
            var prod = Intent(context, ProductActivity::class.java)
            if (getsearchcrops.equals("Tomato") || getsearchcrops.equals("tomato") || getsearchcrops.equals(
                    "Brinjal"
                ) ||
                getsearchcrops.equals("brinjal") || getsearchcrops.equals("Potato") || getsearchcrops.equals(
                    "potato"
                )
            ) {


                //     buypro.edit().putString("searchedquantity",searchquantity).apply()
                startActivity(prod)
            } else {
                Toast.makeText(context, "Invalid Crops", Toast.LENGTH_SHORT).show()
            }

        }
        return view

    }
}