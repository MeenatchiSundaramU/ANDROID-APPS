package com.eagriculture.krish_e.buy

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.eagriculture.krish_e.R
import com.eagriculture.krish_e.R.id.buypayimage
import org.w3c.dom.Text
import java.security.AccessController.getContext

class BuypayActivity : AppCompatActivity() {
    lateinit var cropimage1:ImageView
    lateinit var cropname2:TextView
    lateinit var texthigh:TextView
    lateinit var textmedium:TextView
    lateinit var textlow:TextView
    lateinit var edithigh:EditText
    lateinit var editmedium:EditText
    lateinit var editlow:EditText
    lateinit var checkprice:Button
    lateinit var paybtn:Button
    lateinit var totalprice1:TextView
    lateinit var buySharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.buypay_layout)
        buySharedPreferences=getSharedPreferences(getString(R.string.reg_pass), Context.MODE_PRIVATE)
        var cropimage=intent.getIntExtra("buycropimage",R.drawable.common_dp)
         var cropname=intent.getStringExtra("buycropname")
        var crophigh=intent.getStringExtra("buycrophigh")
        var cropmedium=intent.getStringExtra("buycropmedium")
        var croplow=intent.getStringExtra("buycroplow")
        cropimage1=findViewById(R.id.buypayimage)
        cropname2=findViewById(R.id.buypaycropname)
        texthigh=findViewById(R.id.buyhighvalue)
        textmedium=findViewById(R.id.buymediumvalue)
        textlow=findViewById(R.id.paylowvalue)
        edithigh=findViewById(R.id.payedithigh)
        editmedium=findViewById(R.id.payeditmedium)
        editlow=findViewById(R.id.payeditlow)
        checkprice=findViewById(R.id.checkprice)
        totalprice1=findViewById(R.id.totalpricevalue)
        paybtn=findViewById(R.id.paynow)
        cropimage1.setImageResource(cropimage)
        cropname2.text=cropname
        texthigh.text=crophigh
        textmedium.text=cropmedium
        textlow.text=croplow

        checkprice.setOnClickListener {
            var anshigh=edithigh.text.toString().toInt()
            var ansmedium=editmedium.text.toString().toInt()
            var anslow=editlow.text.toString().toInt()



                var anshigh1 = crophigh?.toInt()
                var ansmedium1 = cropmedium?.toInt()
                var anslow1 = croplow?.toInt()
                var finalhigh: Int? = (anshigh * anshigh1!!)
                var finalmedium: Int? = (ansmedium * ansmedium1!!)
                var finallow: Int? = (anslow * anslow1!!)
                var totalprice: Int? = finalhigh!! + finalmedium!! + finallow!!
                buySharedPreferences.edit().putString("cartcropname", cropname).apply()
                buySharedPreferences.edit().putString("carthighquant", anshigh.toString()).apply()
                buySharedPreferences.edit().putString("cartmedquant", ansmedium.toString()).apply()
                buySharedPreferences.edit().putString("cartlowquant", anslow.toString()).apply()
                buySharedPreferences.edit().putString("carttotal", totalprice.toString()).apply()
                totalprice1.text = totalprice.toString()

        }
        paybtn.setOnClickListener {

         var ind=Intent(this,PaymentSuccesfulActivity::class.java)
            startActivity(ind)


        }




    }
}