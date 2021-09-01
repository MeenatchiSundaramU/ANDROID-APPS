package com.ums.krish_eauthority

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_settle_next.*
import kotlinx.android.synthetic.main.settle_view.view.*

class SettleNextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settle_next)
        val extracts=intent.getStringExtra("KEYSS")
        setSupportActionBar(settletoolbar)
        supportActionBar?.title=extracts!!
        retrievedata(extracts)
        }

    private fun retrievedata(cropnames:String)
    {
        val ref=FirebaseDatabase.getInstance().getReference("/FarmersSettlement/$cropnames")
        ref.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                val adap=GroupAdapter<ViewHolder>()
               p0.children.forEach {

                    val sgets=it.getValue(BuyerActivity.buyershowers.Settle::class.java)
                    if(sgets==null)return
                    adap.add(retry(sgets))
                }
                searchrecycle.adapter=adap
            }

        })
    }
    class retry(val getting: BuyerActivity.buyershowers.Settle): Item<ViewHolder>(){
        override fun getLayout(): Int {
            return R.layout.settle_view
        }

        override fun bind(viewHolder: ViewHolder, position: Int) {
            Picasso.get().load(getting.imageurl).into(viewHolder.itemView.setview_image)
            viewHolder.itemView.setview_cropname.text=getting.cropName
            viewHolder.itemView.setfarmernamevalue.text=getting.farmersName
            viewHolder.itemView.sethqqvalue.text=getting.hqq
            viewHolder.itemView.setmqqvalue.text=getting.mqq
            viewHolder.itemView.setlqqvalue.text=getting.lqq
            viewHolder.itemView.sethqqpricevalue.text=getting.hqqpricerange
            viewHolder.itemView.setmqqpricevalue.text=getting.mqqpricerange
            viewHolder.itemView.setlqqpricevalue.text=getting.lqqpricerange
            viewHolder.itemView.setpaybtn.setOnClickListener {
                val fhqvprice=viewHolder.itemView.setFHQVpricevalue.text.toString()
                val fmqvprice=viewHolder.itemView.setMHQVpricevalue.text.toString()
                val flqvprice=viewHolder.itemView.setLHQVpricevalue.text.toString()
              //  val set=Set(getting.hqq,getting.mqq,getting.lqq,fhqvprice,fmqvprice,flqvprice)
                val ind= Intent(it.context,PriceActivity::class.java)
                ind.putExtra("cname",getting.cropName)
                ind.putExtra("SETTLE0",getting.farmersName)
                ind.putExtra("SETTLE1",getting.hqq)
                ind.putExtra("SETTLE2",getting.mqq)
                ind.putExtra("SETTLE3",getting.lqq)
                ind.putExtra("SETTLE4",fhqvprice)
                ind.putExtra("SETTLE5",fmqvprice)
                ind.putExtra("SETTLE6",flqvprice)
                ind.putExtra("photo",getting.imageurl)
                ind.putExtra("hrange",getting.hqqpricerange)
                ind.putExtra("medrange",getting.mqqpricerange)
                ind.putExtra("lowrange",getting.lqqpricerange)
               // ind.putExtra("GPAY",getting.)
                it.context.startActivity(ind)
            }

        }

    }
}