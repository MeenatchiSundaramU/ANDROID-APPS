package com.eagriculture.krish_e.sell

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import com.eagriculture.krish_e.R
import com.eagriculture.krish_e.buy.EbuyRegisterspalshActivity
import kotlinx.android.synthetic.main.activity_esellregister.*


class EsellregisterActivity : AppCompatActivity() {
    lateinit var passbookbtn: Button
    lateinit var esellidbtn: Button
    lateinit var esellregstate:EditText
    lateinit var esellregdistrict:EditText
    lateinit var esellregdob:EditText
    lateinit var esellregaddress:EditText
    lateinit var esellregpincode:EditText
    lateinit var esellacholdername:EditText
    lateinit var esellbankname:EditText
    lateinit var esellifsc:EditText
    lateinit var esellacno:EditText
    lateinit var esellsubmit:Button
    lateinit var sellRegisterPreferences: SharedPreferences
    lateinit var fragmentManager: FragmentManager
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_esellregister)
        sellRegisterPreferences=getSharedPreferences(getString(R.string.reg_pass), Context.MODE_PRIVATE)
        esellregstate=findViewById(R.id.esellregstate)
        esellregdistrict=findViewById(R.id.esellregdistrict)
        esellregdob=findViewById(R.id.esellregdob)
        esellregaddress=findViewById(R.id.esellregaddress)
        esellregpincode=findViewById(R.id.esellregpincode)
        esellacholdername=findViewById(R.id.esellacholdername)
        esellbankname=findViewById(R.id.esellbankname)
        esellifsc=findViewById(R.id.esellifsc)
        esellacno=findViewById(R.id.esellacno)
        esellsubmit=findViewById(R.id.esellsubmit)
        toolbar=findViewById(R.id.esellregistertoolbar)
        passbookbtn = findViewById(R.id.passbookbtn)
        esellidbtn = findViewById(R.id.esellidbtn)
        val idproof = arrayOf(
            "Voter Id",
            "PAN Card",
            "Aadhar Card",
            "Ration Card",
            "Passport",
            "Driving License"
        )
        setToolbar()
        var idadapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, idproof)
        spinner.adapter = idadapter
        passbookbtn.setOnClickListener {
            var passbookind = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(passbookind, 1000)
        }
        esellidbtn.setOnClickListener {
            var esellidind = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(esellidind, 1001)
        }
        esellsubmit.setOnClickListener {
            var esellactiveind=Intent(this,EbuyRegisterspalshActivity::class.java)
            esellactiveind.putExtra("sell","sell")
            var state:String=esellregstate.text.toString()
            var district:String=esellregdistrict.text.toString()
            var dob:String=esellregdob.text.toString()
            var address:String=esellregaddress.text.toString()
            var pincode:String=esellregpincode.text.toString()
            var acholdername:String=esellacholdername.text.toString()
            var bankname:String=esellbankname.text.toString()
            var ifsc:String=esellifsc.text.toString()
            var acno:String=esellacno.text.toString()
            sellRegisterPreferences.edit().putString("state",state).apply()
            sellRegisterPreferences.edit().putString("district",district).apply()
            sellRegisterPreferences.edit().putString("dob",dob).apply()
            sellRegisterPreferences.edit().putString("address",address).apply()
            sellRegisterPreferences.edit().putString("pincode",pincode).apply()
            sellRegisterPreferences.edit().putString("acholdername",acholdername).apply()
            sellRegisterPreferences.edit().putString("bankname",bankname).apply()
            sellRegisterPreferences.edit().putString("ifsc",ifsc).apply()
            sellRegisterPreferences.edit().putString("acno",acno).apply()
            sellRegisterPreferences.edit().putBoolean("checkregister",true).apply()



            startActivity(esellactiveind)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000) {
            var passbookimage = data?.extras?.get("data") as Bitmap
            esellpassbook.setImageBitmap(passbookimage)
        } else if (requestCode == 1001) {
            var idproof = data?.extras?.get("data") as Bitmap
            esellproofimage.setImageBitmap(idproof)
        }

    }
    fun setToolbar()
    {
        setSupportActionBar(toolbar)
        supportActionBar?.title="E-Sell Register"

    }
}
