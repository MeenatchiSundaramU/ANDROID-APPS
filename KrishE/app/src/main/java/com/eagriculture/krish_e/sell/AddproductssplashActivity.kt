package com.eagriculture.krish_e.sell

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.eagriculture.krish_e.R

class AddproductssplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addproductssplash)
        Handler().postDelayed(
            {
                var ind= Intent(this, EsellActivity::class.java)
                startActivity(ind)
            },2000)

    }

    override fun onPause() {
        finish()
        super.onPause()
    }
}