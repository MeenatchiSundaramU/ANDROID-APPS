package com.ums.krish_eauthority

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_price.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.round

class PriceActivity : AppCompatActivity() {
    lateinit var total:String
    var hq:String?=null
    var mq:String?=null
    var lq:String?=null
    var hrange:String?=null
    var medrange:String?=null
    var lowrange:String?=null
    var farmername:String?=null
    var gpayno:String?=null
     var photo:String?=null
    var cname:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_price)
        farmername=intent.getStringExtra("SETTLE0")
        hq=intent.getStringExtra("SETTLE1")
        mq=intent.getStringExtra("SETTLE2")
        lq=intent.getStringExtra("SETTLE3")
        val hp=intent.getStringExtra("SETTLE4")
        val mp=intent.getStringExtra("SETTLE5")
        val lp=intent.getStringExtra("SETTLE6")
        gpayno="9360685327"
        photo=intent.getStringExtra("photo")
        cname=intent.getStringExtra("cname")
        hrange=intent.getStringExtra("hrange")
        medrange=intent.getStringExtra("medrange")
        lowrange=intent.getStringExtra("lowrange")
        calculatefn(hq!!,mq!!,lq!!,hp!!,mp!!,lp!!)
        pricefarmersnamevalue.text=farmername
        pricehqqvalue.text=hq
        pricemqqvalue.text=mq
        pricelqqvalue.text=lq
        totalpricenamevalue.text=total
        gpaynovalue.text=gpayno
        setcropnamealue.text=cname
        setpay.setOnClickListener {
            payprogreesbar.visibility= View.VISIBLE
            uploaddata()
        }
    }
    private fun calculatefn(HQ:String,MQ:String,LQ:String,HP:String,MP:String,LP:String)
    {
        val highprice= round((HQ.toDouble()*HP.toDouble()))
        val medprice= round((MQ.toDouble()*MP.toDouble()))
        val lowprice= round((LQ.toDouble()*LP.toDouble()))
        total=(highprice+medprice+lowprice).toString()

    }
    @SuppressLint("SimpleDateFormat")
    private fun uploaddata()
    {
        val calendar=Calendar.getInstance()
        val date=SimpleDateFormat("dd-MM-y").format(calendar.time).toString()
        val time=SimpleDateFormat("hh:mm:ss a").format(calendar.time).toString()
        val transid=UUID.randomUUID().toString()
        val ref=FirebaseDatabase.getInstance().getReference("/PaymentHistory/$cname/$date").push()
        val setting=payhist(cname!!,photo!!,farmername!!,gpayno!!,hq!!,mq!!,lq!!,hrange!!,medrange!!,lowrange!!,total,date,time,transid)
        ref.setValue(setting).addOnSuccessListener {
            payprogreesbar.visibility= View.GONE
            val ind=Intent(this,PaidSplash::class.java)
            startActivity(ind)
            finish()
        }

    }
    class payhist(val cropname:String,val cropphoto:String,val farmersname:String,val gpayno:String,val hqq:String,val mqq:String,
                  val lqq:String,val hpr:String,val mpr:String,val lpr:String,val totalprice:String,val date:String,val time:String,val transid:String){
        constructor() : this("","","","","","","","",""
        ,"","","","","")
    }
}