package com.eagriculture.krish_e.buy

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import com.eagriculture.krish_e.R

class EbuyRegister : AppCompatActivity() {
    lateinit var ebuyregstate: EditText
    lateinit var ebuyregdistrict: EditText
    lateinit var ebuyregdob: EditText
    lateinit var ebuyregaddress: EditText
    lateinit var ebuyregpincode: EditText
    lateinit var buyRegisterPreferences: SharedPreferences
    lateinit var ebuysubmit:Button
    lateinit var ebuytoolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ebuy_register)
        buyRegisterPreferences=getSharedPreferences(getString(R.string.reg_pass), Context.MODE_PRIVATE)
        ebuyregstate=findViewById(R.id.ebuyregstate)
        ebuyregdistrict=findViewById(R.id.ebuyregdistrict)
        ebuyregdob=findViewById(R.id.ebuyregdob)
        ebuyregaddress=findViewById(R.id.ebuyregaddress)
        ebuyregpincode=findViewById(R.id.ebuyregpincode)
        ebuysubmit=findViewById(R.id.ebuysubmit)
        ebuytoolbar=findViewById(R.id.ebuyregistertoolbar)
        onsetToolbar()
        ebuysubmit.setOnClickListener {
            var buyind=Intent(this,EbuyRegisterspalshActivity::class.java)
            buyind.putExtra("buy","buy")
            var buystate:String=ebuyregstate.text.toString()
            var buydistrict:String=ebuyregdistrict.text.toString()
            var buydob:String=ebuyregdob.text.toString()
            var buyaddress:String=ebuyregaddress.text.toString()
            var buypincode:String=ebuyregpincode.text.toString()

            buyRegisterPreferences.edit().putString("buystate",buystate).apply()
            buyRegisterPreferences.edit().putString("buydistrict",buydistrict).apply()
            buyRegisterPreferences.edit().putString("buydob",buydob).apply()
            buyRegisterPreferences.edit().putString("buyaddress",buyaddress).apply()
            buyRegisterPreferences.edit().putString("buypincode",buypincode).apply()
            buyRegisterPreferences.edit().putBoolean("buyreg",true).apply()
            startActivity(buyind)

        }

    }
    fun onsetToolbar()
    {
        setSupportActionBar(ebuytoolbar)
        supportActionBar?.title="E-Buy Register"
    }
}