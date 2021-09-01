package com.ums.tesfood.Authentication

import HomeActivity.FleshersSplashActivity
import HomeActivity.HomeActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ums.tesfood.Authentication.SignInActivity
import com.ums.tesfood.R
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    lateinit var  email:String
    lateinit var pass:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        supportActionBar!!.hide()
        login.setOnClickListener {
            checkUser()
        }
        sign_up.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
    }

    private fun checkUser()
    {
        email=sign_email.text.toString()
        pass=sign_pass.text.toString()
        when
        {
            TextUtils.isEmpty(email)->{
                sign_email.error="Email Required"
                sign_email.requestFocus()
            }
            TextUtils.isEmpty(pass)->{
                sign_pass.error="Email Required"
                sign_pass.requestFocus()
            }
            else->{
                sign_in_progress.visibility= View.VISIBLE
                  val sign_in_auth=FirebaseAuth.getInstance()
                sign_in_auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
                    if(it.isSuccessful)
                    {
                        sign_in_progress.visibility= View.INVISIBLE
                        startActivity(Intent(this,FleshersSplashActivity::class.java))
                        finish()
                    }
                    else
                    {
                        sign_in_progress.visibility= View.INVISIBLE
                        Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if(FirebaseAuth.getInstance().uid!=null)
        {
            startActivity(Intent(this,FleshersSplashActivity::class.java))
            finish()
        }
    }

}