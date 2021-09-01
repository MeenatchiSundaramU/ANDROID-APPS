package com.UMS.chatsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast

class ForgetsplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgetsplash)
        Handler().postDelayed({
            val ind= Intent(this,LoginActivity::class.java)
            startActivity(ind)
        },2000)
    }
}