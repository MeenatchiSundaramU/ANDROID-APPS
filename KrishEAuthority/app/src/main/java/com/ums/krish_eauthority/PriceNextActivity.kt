package com.ums.krish_eauthority

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_price_next.*
import kotlinx.android.synthetic.main.activity_settle_next.*
import kotlinx.android.synthetic.main.history_view.view.*
import kotlinx.android.synthetic.main.settle_view.view.*

class PriceNextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_price_next)
        val hist = intent.getStringExtra("HIST")
        setSupportActionBar(histoolbar)
        supportActionBar?.title = hist!!

        histsearchbtn.setOnClickListener {
            val getdate = histdatevalue.text.toString()
            retrievedata(hist, getdate)
        }
    }

    private fun retrievedata(cname: String, date: String) {
        val ref = FirebaseDatabase.getInstance().getReference("/PaymentHistory/$cname/$date")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                val adap = GroupAdapter<ViewHolder>()
                p0.children.forEach {

                    val sgets = it.getValue(PriceActivity.payhist::class.java)
                    if (sgets == null) return
                    adap.add(retry(sgets))
                }
                histrecycle.adapter = adap
            }

        })
    }

    class retry(val getting: PriceActivity.payhist) : Item<ViewHolder>() {
        override fun getLayout(): Int {
            return R.layout.history_view
        }

        override fun bind(viewHolder: ViewHolder, position: Int) {
          Picasso.get().load(getting.cropphoto).into(viewHolder.itemView.histsetview_image)
            viewHolder.itemView.histsetview_cropname.text = getting.cropname
            viewHolder.itemView.histsetfarmernamevalue.text = getting.farmersname
            viewHolder.itemView.histsethqqvalue.text = getting.hqq
            viewHolder.itemView.histsetmqqvalue.text = getting.mqq
            viewHolder.itemView.histsetlqqvalue.text = getting.lqq
            viewHolder.itemView.histsethqqpricevalue.text = getting.hpr
            viewHolder.itemView.histsetmqqpricevalue.text = getting.mpr
            viewHolder.itemView.histsetlqqpricevalue.text = getting.lpr
            viewHolder.itemView.histtotal.text = getting.totalprice
            viewHolder.itemView.histgpayvalue.text = getting.gpayno
            viewHolder.itemView.histransidvalue.text = getting.transid
            viewHolder.itemView.histranstimevalue.text = getting.time
            viewHolder.itemView.histransvalue.text = getting.date

        }
    }
}