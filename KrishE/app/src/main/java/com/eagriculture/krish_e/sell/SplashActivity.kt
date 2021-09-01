package com.eagriculture.krish_e.sell

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.eagriculture.krish_e.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
      Handler().postDelayed({
          var logind=Intent(this@SplashActivity,
              LoginActivity::class.java)
          startActivity(logind)
      },2000)

    }

    override fun onPause() {
        finish()
        super.onPause()
    }
}