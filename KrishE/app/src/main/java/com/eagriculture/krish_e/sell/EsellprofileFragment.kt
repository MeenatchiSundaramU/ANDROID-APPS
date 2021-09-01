package com.eagriculture.krish_e.sell

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.eagriculture.krish_e.R


class EsellprofileFragment : Fragment() {
lateinit var profilename: EditText
    lateinit var profilemobile:EditText
    lateinit var profileemail:EditText
    lateinit var profiledob:EditText
    lateinit var profilestate:EditText
    lateinit var profiledistrict:EditText
    lateinit var profileaddress:EditText
    lateinit var profilepincode:EditText
    lateinit var profilebankname:EditText
    lateinit var profileacholder:EditText
    lateinit var profileacno:EditText
    lateinit var profileifsc:EditText
    lateinit var SharedPreferences:SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         var view=inflater.inflate(R.layout.fragment_esellprofile, container, false)
        SharedPreferences=getContext()!!.getSharedPreferences(getString(R.string.reg_pass),MODE_PRIVATE)
        profilename=view.findViewById(R.id.profilenamevalue)
        profilemobile=view.findViewById(R.id.profilemobilevalue)
        profileemail=view.findViewById(R.id.profileemailvalue)
        profiledob=view.findViewById(R.id.profiledobvalue)
        profilestate=view.findViewById(R.id.profilestatevalue)
        profiledistrict=view.findViewById(R.id.profiledistrictvalue)
        profileaddress=view.findViewById(R.id.profileaddressvalue)
        profilepincode=view.findViewById(R.id.profilepincodevalue)
        profilebankname=view.findViewById(R.id.profilebanknamevalue)
        profileacholder=view.findViewById(R.id.profilebankholdernamevalue)
        profileacno=view.findViewById(R.id.profilebankacnovalue)
        profileifsc=view.findViewById(R.id.profileifscvalue)
        var name:String?=SharedPreferences.getString("name","mano")
        var mobile:String?=SharedPreferences.getString("mobilenumber","mano")
        var email:String?=SharedPreferences.getString("email","mano")
        var dob:String?=SharedPreferences.getString("dob","mano")
        var state:String?=SharedPreferences.getString("state","mano")
        var district:String?=SharedPreferences.getString("district","mano")
        var address:String?=SharedPreferences.getString("address","mano")
        var pincode:String?=SharedPreferences.getString("pincode","mano")
        var bankname:String?=SharedPreferences.getString("bankname","mano")
        var acholdername:String?=SharedPreferences.getString("acholdername","mano")
        var acno:String?=SharedPreferences.getString("acno","mano")
        var ifsc:String?=SharedPreferences.getString("ifsc","mano")
        profilename.setText(name.toString())
        profilemobile.setText(mobile.toString())
        profileemail.setText(email.toString())
        profiledob.setText(dob.toString())
        profilestate.setText(state.toString())
        profiledistrict.setText(district.toString())
        profileaddress.setText(address.toString())
        profilepincode.setText(pincode.toString())
        profilebankname.setText(bankname.toString())
        profileacholder.setText(acholdername.toString())
        profileacno.setText(acno.toString())
        profileifsc.setText(ifsc.toString())



        return view
    }


}