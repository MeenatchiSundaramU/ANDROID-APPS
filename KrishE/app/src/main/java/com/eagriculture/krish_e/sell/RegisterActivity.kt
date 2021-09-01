package com.eagriculture.krish_e.sell

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.eagriculture.krish_e.R
import com.eagriculture.krish_e.buy.EbuyRegister
import com.eagriculture.krish_e.buy.EbuyRegisterspalshActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.register_activity.*
import java.util.concurrent.TimeUnit

class RegisterActivity : AppCompatActivity() {
  //  lateinit var clicks: String
    lateinit var regimobileno:String
    lateinit var callBacks:PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)
        verifyphone.setOnClickListener {
            regverify()
        }

    }
    private fun regverify() {
        var regname = name.text.toString()
        regimobileno = regmobileno.text.toString()
        var regpasswords = regpassword.text.toString()
        if (regname.isEmpty()) {
            name.error = "Enter Valid Name"
            name.requestFocus()
            return
        } else if (!male.isChecked && !female.isChecked) {
            Toast.makeText(this, "Select the Gender", Toast.LENGTH_SHORT).show()
            return
        } else if (regimobileno.isEmpty() || regimobileno.length > 13) {
            if (regimobileno.length > 13) {
                regmobileno.error = "Enter 10 digit mobile number"
                regmobileno.requestFocus()
            }
            regmobileno.error = "Enter Mobile number"
            regmobileno.requestFocus()
            return
        } else if (regpasswords.isEmpty() || regpasswords.length < 6 || regpasswords != regconfpass.text.toString()) {
            if (regpasswords != regconfpass.text.toString()) {
                regconfpass.error = "Passwords do not match"
                regconfpass.requestFocus()
            } else {
                regpassword.error = "Enter Valid Passwords"
                regconfpass.error = "Enter Valid Passwords"
                regpassword.requestFocus()
                regconfpass.requestFocus()
            }
            return
        } else {
            if (male.isChecked) {
                var reggender: String = "male"
            } else {
                var reggender: String = "female"
            }
            verifyphone()
            workerreg.visibility=View.VISIBLE
        }
    }
    private fun verifyphone()
    {
        CALLBACK()
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            regimobileno,
            60,
            TimeUnit.SECONDS,
            this,
            callBacks
        )
    }
    private fun CALLBACK()
    {
        callBacks=object :PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                workerreg.visibility=View.GONE
                signin(credential)
            }

            override fun onVerificationFailed(p0: FirebaseException) {
              toast(p0.message.toString())
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
            }
        }
    }
    private fun signin(credential: PhoneAuthCredential)
    {
        var mauth:FirebaseAuth
        mauth.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful){
                toast("Phone Verified Success")
            }
        }
    }
    private fun toast(message:String)
    {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}