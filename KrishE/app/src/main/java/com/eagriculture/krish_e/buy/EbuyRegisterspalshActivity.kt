package com.eagriculture.krish_e.buy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.eagriculture.krish_e.sell.EsellActivity
import com.eagriculture.krish_e.sell.LoginActivity
import com.eagriculture.krish_e.R

class EbuyRegisterspalshActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ebuy_registerspalsh)
        var get1 = intent.getStringExtra("buy")
        var get2 = intent.getStringExtra("sell")
        var get3=intent.getStringExtra("register")

            Handler().postDelayed({
                if (get1!=null) {
                    var regind = Intent(this, EbuyActivity::class.java)
                    startActivity(regind)
                }
                else if(get3!=null)
                {
                    var regind2 = Intent(this, LoginActivity::class.java)
                    startActivity(regind2)
                }
                else
                {
                    var regind1 = Intent(this, EsellActivity::class.java)
                    startActivity(regind1)
                }


            }, 2000)
        }

    override fun onPause() {
        finish()
        super.onPause()
    }
}