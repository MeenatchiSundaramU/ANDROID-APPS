package com.UMS.chatsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_log.*

class LoginActivity : AppCompatActivity() {
    lateinit var names: String
  // lateinit var forgetprogressbar:ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)
        supportActionBar?.hide()
        newuser.setOnClickListener {
            val logind= Intent(this,RegisterActivity::class.java).apply {
                flags=Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(logind)
        }
        logbtn.setOnClickListener {
            logprogress()
        }
        forgotpass.setOnClickListener {
            val ind=Intent(this,ForgetworkActivity::class.java)
            startActivity(ind)
        }
    }
    private fun logprogress()
    {
        val emails=logemail.text.toString()
        val pass=logpass.text.toString()
        if(!Patterns.EMAIL_ADDRESS.matcher(emails).matches()||emails.isEmpty()){
            logemail.requestFocus()
            logemail.error="Enter Valid Email"
            return
        }
        if(pass.isEmpty()||logpass.text.toString().length<=7)
        {
            logpass.requestFocus()
            logpass.error="Enter valid Password"
            return
        }
        logpro.visibility=View.VISIBLE
        FirebaseAuth.getInstance().signInWithEmailAndPassword(emails,pass).addOnCompleteListener {
            if(it.isSuccessful){
                logpro.visibility=View.GONE
                val ind=Intent(this,HomeActivity::class.java).apply {
                    flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                startActivity(ind)
            }
        }.addOnFailureListener {
            logpro.visibility=View.GONE
            Toast.makeText(this,it.message.toString(),Toast.LENGTH_SHORT).show()
        }
    }

}