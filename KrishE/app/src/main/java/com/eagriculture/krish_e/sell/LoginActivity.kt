package com.eagriculture.krish_e.sell

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.eagriculture.krish_e.R

class LoginActivity : AppCompatActivity() {
    lateinit var mobilenumber:EditText
    lateinit var password:EditText
    lateinit var login:Button
    lateinit var forgotpass:TextView
    lateinit var newuser:TextView
    lateinit var loginPreferences: SharedPreferences
    lateinit var checkmobile:String
    lateinit var checkpass:String
    var check:Boolean?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        loginPreferences=getSharedPreferences(getString(R.string.reg_pass), Context.MODE_PRIVATE)
        check=loginPreferences.getBoolean("checklogin",false)
        if(check==true)
        {
            var homeind=Intent(this@LoginActivity,
                HomeActivity::class.java)
            startActivity(homeind)
            finish()

        }
        mobilenumber=findViewById(R.id.mobileno)
        password=findViewById(R.id.password)
        login=findViewById(R.id.login)
        forgotpass=findViewById(R.id.forgetpassword)
        newuser=findViewById(R.id.register)

        var sharedmobile:String?=loginPreferences.getString("mobilenumber","zero")
        var sharedpass:String?=loginPreferences.getString("password","zero")

        newuser.setOnClickListener {


            var logind= Intent(this@LoginActivity,
                RegisterActivity::class.java)
            startActivity(logind)
        }
        login.setOnClickListener {
            checkmobile=mobilenumber.text.toString()
            checkpass=password.text.toString()
            if(sharedmobile =="zero"&& sharedpass=="zero")
            {
                Toast.makeText(this,"Invalid User",Toast.LENGTH_SHORT).show()
            }
            else if(sharedmobile==checkmobile && sharedpass==checkpass)
            {
                var homeind=Intent(this@LoginActivity,
                    HomeActivity::class.java)
                loginPreferences.edit().putBoolean("checklogin",true).apply()
                startActivity(homeind)
            }
            else
            {
                Toast.makeText(this,"Invalid User",Toast.LENGTH_SHORT).show()
            }

        }

    }
}