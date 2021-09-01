package com.ums.krish_eauthority

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class PaidSplash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paid_splash)
        Handler().postDelayed({
            val ind= Intent(this,SettleNextActivity::class.java)
            startActivity(ind)
        },2000
        )
    }
}