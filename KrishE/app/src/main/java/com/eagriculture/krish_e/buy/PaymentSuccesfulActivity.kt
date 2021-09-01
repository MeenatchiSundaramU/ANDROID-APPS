package com.eagriculture.krish_e.buy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.eagriculture.krish_e.R

class PaymentSuccesfulActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_succesful)
        Handler().postDelayed({
            var returnsearch= Intent(this,EbuyActivity::class.java)
            startActivity(returnsearch)
        },2000)
    }

    override fun onPause() {
        finish()
        super.onPause()
    }
}
