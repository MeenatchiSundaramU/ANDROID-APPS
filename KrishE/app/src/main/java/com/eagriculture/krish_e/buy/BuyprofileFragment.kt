package com.eagriculture.krish_e.buy

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.eagriculture.krish_e.R


class BuyprofileFragment : Fragment() {

    lateinit var ebuyprofilename: EditText
    lateinit var ebuyprofilemobile: EditText
    lateinit var ebuyprofileemail: EditText
    lateinit var ebuyprofiledob: EditText
    lateinit var ebuyprofilestate: EditText
    lateinit var ebuyprofiledistrict: EditText
    lateinit var ebuyprofileaddress: EditText
    lateinit var ebuyprofilepincode: EditText

    lateinit var SharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var buyprofview=inflater.inflate(R.layout.fragment_buyprofile, container, false)
        SharedPreferences=getContext()!!.getSharedPreferences(getString(R.string.reg_pass),
            Context.MODE_PRIVATE
        )
        ebuyprofilename=buyprofview.findViewById(R.id.buyprofilenamevalue)
        ebuyprofilemobile=buyprofview.findViewById(R.id.buyprofilemobilevalue)
        ebuyprofileemail=buyprofview.findViewById(R.id.buyprofileemailvalue)
        ebuyprofiledob=buyprofview.findViewById(R.id.buyprofiledobvalue)
        ebuyprofilestate=buyprofview.findViewById(R.id.buyprofilestatevalue)
        ebuyprofiledistrict=buyprofview.findViewById(R.id.buyprofiledistrictvalue)
        ebuyprofileaddress=buyprofview.findViewById(R.id.buyprofileaddressvalue)
        ebuyprofilepincode=buyprofview.findViewById(R.id.buyprofilepincodevalue)

        var buyname:String?=SharedPreferences.getString("name","mano")
        var buymobile:String?=SharedPreferences.getString("mobilenumber","mano")
        var buyemail:String?=SharedPreferences.getString("email","mano")
        var buydob:String?=SharedPreferences.getString("buydob","mano")
        var buystate:String?=SharedPreferences.getString("buystate","mano")
        var buydistrict:String?=SharedPreferences.getString("buydistrict","mano")
        var buyaddress:String?=SharedPreferences.getString("buyaddress","mano")
        var buypincode:String?=SharedPreferences.getString("buypincode","mano")
        ebuyprofilename.setText(buyname.toString())
        ebuyprofilemobile.setText(buymobile.toString())
        ebuyprofileemail.setText(buyemail.toString())
        ebuyprofiledob.setText(buydob.toString())
        ebuyprofilestate.setText(buystate.toString())
        ebuyprofiledistrict.setText(buydistrict.toString())
        ebuyprofileaddress.setText(buyaddress.toString())
        ebuyprofilepincode.setText(buypincode.toString())
        return buyprofview
    }

}