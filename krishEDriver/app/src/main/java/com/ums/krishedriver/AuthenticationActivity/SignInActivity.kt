package com.ums.krishedriver.AuthenticationActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.ums.krishedriver.Mainfragment.MainActivity
import com.ums.krishedriver.R
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        supportActionBar?.title="Krish-E Driver Login"
        signin.setOnClickListener {
            allow()
        }
        signup.setOnClickListener {
            val ind=Intent(this,SignupActivity::class.java)
            startActivity(ind)
            finish()
        }
    }
    private fun allow()
    {
        val email=sign_email.text.toString()
        val pass=sign_pass.text.toString()
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass).addOnCompleteListener {
            if(it.isSuccessful)
            {
                startActivity(Intent(this,
                    MainActivity::class.java))
                finish()
            }
            else{
                Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                return@addOnCompleteListener
            }
        }
    }
}