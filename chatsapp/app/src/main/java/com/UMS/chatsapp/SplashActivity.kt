package com.UMS.chatsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.chatssplash)
        Handler().postDelayed({
            val ind= Intent(this,HomeActivity::class.java)
            startActivity(ind)
            finish()
        },2000)
    }
}