package com.eagriculture.krish_e.sell

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.eagriculture.krish_e.R
import com.eagriculture.krish_e.buy.EbuyActivity
import com.eagriculture.krish_e.buy.EbuyRegister

class HomeActivity : AppCompatActivity() {

    lateinit var homeesellbutton:Button
    lateinit var homeebuybutton:Button
    lateinit var checksharedpreferences:SharedPreferences
    var checksellreg:Boolean?=null
    var buyreg:Boolean?=null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        checksharedpreferences=getSharedPreferences(getString(R.string.reg_pass), Context.MODE_PRIVATE)
        checksellreg=checksharedpreferences.getBoolean("checkregister",false)
        buyreg=checksharedpreferences.getBoolean("buyreg",false)
        homeesellbutton=findViewById(R.id.homeesellbtn)
        homeebuybutton=findViewById(R.id.homeebuybtn)
       
        homeesellbutton.setOnClickListener {
            if (checksellreg == true) {
                var sellind = Intent(this@HomeActivity, EsellActivity::class.java)
                startActivity(sellind)
            } else {
                var sellind = Intent(this@HomeActivity, EsellregisterActivity::class.java)
                startActivity(sellind)
            }
        }
        homeebuybutton.setOnClickListener {
            if (buyreg == true) {
                var buyind = Intent(
                    this@HomeActivity,
                    EbuyActivity::class.java
                )
                startActivity(buyind)
            } else {

                var buyind = Intent(
                    this@HomeActivity,
                    EbuyRegister::class.java
                )
                startActivity(buyind)
            }
        }


    }
}