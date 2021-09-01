package com.ums.krish_eauthority

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_buyer.*
import kotlinx.android.synthetic.main.buyer_innerview.view.*
import java.util.*
import kotlin.math.round

class BuyerActivity : AppCompatActivity() {
    lateinit var highquality1:String
    lateinit var highquality2:String
    lateinit var mediumquality1:String
    lateinit var mediumquality2:String
    lateinit var lowquality1:String
    lateinit var lowquality2:String
    lateinit var buyhighprice:String
    lateinit var buymedprice:String
    lateinit var buylowprice:String
    var extract:updata?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buyer)
        extract=intent.getParcelableExtra<updata>("ANS")
        if(extract==null)return
        setSupportActionBar(buyeractionbar)
        supportActionBar?.title=extract!!.cropnames
        val baseprice:Float=extract!!.baseprices.toFloat()
        val marketprice:Float=extract!!.marketprices.toFloat()
        sumupdata(baseprice,marketprice)
        //dummydata(extract!!)
        retrievedata(extract!!.cropnames)
    }
   /* private fun dummydata(extrass:updata)
    {
        val adap=GroupAdapter<ViewHolder>()
       adap.add(users(extrass,highquality1,highquality2,mediumquality1,mediumquality2,lowquality1,lowquality2,buyhighprice,buymedprice,buylowprice))
        buyerinnerrecycle.adapter=adap

    }*/

    private fun sumupdata(bprice:Float,mprice:Float)
    {
        var transportprice:Double=0.35
        var baseprice:Double=bprice.toDouble()
        var marketprice:Double=mprice.toDouble()
        var diff:Double=marketprice-baseprice
        var hqlow=0.25*diff
        var hqhigh=0.5*diff
        if(hqhigh<baseprice){
            hqlow=baseprice
            hqhigh=baseprice
        }
        highquality1= round(hqlow).toString()
        highquality2= round(hqhigh).toString()
        var medlow=0.25*diff*0.9
        var medhigh=0.5*diff*0.9
        if(medhigh<baseprice){
            medhigh=baseprice
            medlow=baseprice
        }
        mediumquality1= round(medlow).toString()
        mediumquality2= round(medhigh).toString()
        var lowlow=0.25*diff*0.9*0.7
        var lowhigh=0.5*diff*0.9*0.7
        if(lowhigh<baseprice){
            lowhigh=baseprice
            lowlow=baseprice
        }
        lowquality1= round(lowlow).toString()
        lowquality2= round(lowhigh).toString()
        buyhighprice= round((hqhigh+(transportprice*hqhigh))).toString()
        var med=hqhigh*0.9
        if(med<baseprice)
        {
            med=baseprice
        }
        buymedprice= round(((med)+(transportprice*med))).toString()
        var low=hqhigh*0.8
        if(low<baseprice)
        {
            low=baseprice
        }
        buylowprice= round(((low)+(transportprice*low))).toString()
    }
   private fun retrievedata(cropname:String)
    {
        val ref=FirebaseDatabase.getInstance().getReference("/AllsellerProducts/Products/$cropname")
        ref.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                val adap=GroupAdapter<ViewHolder>()
                for(h in p0.children)
                {
                    var ret=h.getValue(buyershowdata::class.java)
                    if(ret==null)return
                    adap.add(buyershowers(ret,extract!!,highquality1,highquality2,mediumquality1,mediumquality2
                    ,lowquality1,lowquality2,buyhighprice,buymedprice,buylowprice))
                }
                buyerinnerrecycle.adapter=adap
            }
        })
    }
  class buyershowers(val buys:buyershowdata,val extr:updata,val high1:String,val high2:String,val med1:String,val med2:String,
                       val low1:String,val low2:String,val bhigh:String,val bmed:String,val blow:String):Item<ViewHolder>()
    {
        override fun getLayout(): Int {
            return R.layout.buyer_innerview
        }

        @RequiresApi(Build.VERSION_CODES.N)
        @SuppressLint("SetTextI18n")
        override fun bind(viewHolder: ViewHolder, position: Int) {
            Picasso.get().load(extr.uploadphotoss).into(viewHolder.itemView.view_image)
            viewHolder.itemView.farmernamevalue.text=buys.userName
            viewHolder.itemView.hqqvalue.text=buys.highquantity
            viewHolder.itemView.mqqvalue.text=buys.mediumquantity
            viewHolder.itemView.lqqvalue.text=buys.lowquantity
           viewHolder.itemView.hqqpricevalue.text="$high1-$high2"
           viewHolder.itemView.mqqpricevalue.text="$med1-$med2"
            viewHolder.itemView.lqqpricevalue.text="$low1-$low2"
            viewHolder.itemView.addbtns.setOnClickListener {
                retrievedatas(buys.cropname,it)
                farmersettlement(buys.cropname,it)
            }

        }
        @SuppressLint("SimpleDateFormat")
        @RequiresApi(Build.VERSION_CODES.N)
        private fun farmersettlement(cname:String, view1:View)
        {
            // val calendar=Calendar.getInstance()
            //  val date=SimpleDateFormat("dd-MM-y").format(calendar.time).toString()
            val ref2=FirebaseDatabase.getInstance().getReference("/FarmersSettlement/$cname").push()
            val set=Settle(cname,buys.userName,buys.highquantity,buys.mediumquantity,buys.lowquantity,"$high1-$high2","$med1-$med2",
                "$low1-$low2",extr.uploadphotoss)
            ref2.setValue(set).addOnSuccessListener {
                Log.d("select","Added Successfully")
            }

        }
        class Settle(val cropName:String,val farmersName:String,val hqq:String,val mqq:String,val lqq:String,val hqqpricerange:String,val mqqpricerange:String,
                     val lqqpricerange:String,val imageurl:String){
            constructor() : this("","","","","","","","","")
        }

        private fun retrievedatas(cropnames: String,views:View) {
            var i=1
            val ref = FirebaseDatabase.getInstance().getReference("/BuyerShow/$cropnames")
            ref.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    val getinitial = p0.getValue(buyershowszero::class.java)
                    if (getinitial == null||i>1){
                        Log.d("select","GETTING")
                        return
                    } else {
                        //upload(getinitial, cropnames)
                        val updatehighquant=(getinitial.highqualityQuantity.toDouble()+buys.highquantity.toDouble()).toString()
                        val updatemedquant=(getinitial.mediumqualityQuantity.toDouble()+buys.mediumquantity.toDouble()).toString()
                        val updatelowhquant=(getinitial.lowqualityQuantity.toDouble()+buys.lowquantity.toDouble()).toString()
                        val ref2=FirebaseDatabase.getInstance().getReference("/BuyerShow/$cropnames")
                        val setfinal=buyershowszero(cropnames,updatehighquant,updatemedquant,updatelowhquant,bhigh,bmed,
                            blow,getinitial.photouri)
                        i=i+1
                        Log.d("select",i.toString())
                        ref2.setValue(setfinal)
                    }
                }

            })
        }

    }
   class buyershowdata(val cropname:String,val cropquantity:String,val harvestdate:String,val highquantity:String,
                        val lowquantity:String,val mediumquantity:String,val userGpay:String,val userName:String,val userid:String){
        constructor() : this("","","","","","","","","")
    }
 /* class users(val ext:updata,val h1:String,val h2:String,val m1:String,val m2:String,val l1:String,val l2:String,val bhigh:String,val bmed:String,
   val blow:String):Item<ViewHolder>() {
       override fun getLayout(): Int {
           return R.layout.buyer_innerview
       }

       @RequiresApi(Build.VERSION_CODES.N)
       @SuppressLint("SetTextI18n")
       override fun bind(viewHolder: ViewHolder, position: Int) {
           Picasso.get().load(ext.uploadphotoss).into(viewHolder.itemView.view_image)
           viewHolder.itemView.view_cropname.text = ext.cropnames
           viewHolder.itemView.farmernamevalue.text = "Mano"
           viewHolder.itemView.hqqvalue.text = "20.0"
           viewHolder.itemView.mqqvalue.text = "100.0"
           viewHolder.itemView.lqqvalue.text = "250.0"
           viewHolder.itemView.hqqpricevalue.text = "$h1-$h2"
           viewHolder.itemView.mqqpricevalue.text = "$m1-$m2"
           viewHolder.itemView.lqqpricevalue.text = "$l1-$l2"

       }

       /*  private fun upload(getsini:buyershowszero,crop:String)
       {
           var j:Int=1
           val updatehighquant=(getsini.highqualityQuantity.toDouble()+"20.00".toDouble()).toString()
           val updatemedquant=(getsini.mediumqualityQuantity.toDouble()+"10.00".toDouble()).toString()
           val updatelowhquant=(getsini.highqualityQuantity.toDouble()+"30.00".toDouble()).toString()
           val ref2=FirebaseDatabase.getInstance().getReference("/BuyerShow/$crop")
           val setfinal=buyershowszero(updatehighquant,updatemedquant,updatelowhquant,getsini.photouri)
           if(j==1) {
               j=j+1
               ref2.setValue(setfinal).addOnSuccessListener {
                   Log.d("select", "Success")

               }
           }
                   else{
               Log.d("select","Outing")
               return
           }
               .addOnFailureListener {
                   Log.d("select","Failure")
               }
       }*/

   }*/

}