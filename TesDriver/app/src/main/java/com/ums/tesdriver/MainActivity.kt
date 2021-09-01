package com.ums.tesdriver

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home_view.view.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       /* histsearchbtn.setOnClickListener {
//            val district=district_value.text.toString()
//            val city=city_value.text.toString()
        }*/
            retrieveData()

    }
    @SuppressLint("SimpleDateFormat")
    private fun retrieveData()
    {
        val calendar=Calendar.getInstance()
        var date=SimpleDateFormat("dd-MM-y").format(calendar.time).toString()
        var ref=FirebaseDatabase.getInstance().getReference("/DeliveryOrder/Erode/Bhavani/$date")
        ref.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                val adap=GroupAdapter<ViewHolder>()
                for(h in p0.children)
                {
                    val get_data=h.getValue(Delivery::class.java)
                    if(get_data!=null)
                    {
                        adap.add(Deliverys(get_data))
                    }
                }
                adap.setOnItemClickListener { item, view ->
                    val composed=item as Deliverys
                    val datas=composed.del_data
                   val ind= Intent(view.context,MapActivity::class.java)
                    ind.putExtra("LAT",datas)
                    startActivity(ind)
                }
                home_recycle.adapter=adap

            }
        })
    }
    class Deliverys(val del_data:Delivery):Item<ViewHolder>()
    {
        override fun getLayout(): Int {
            return R.layout.home_view
        }

        override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.itemView.req_flesh_name.text=del_data.fleshtype
            viewHolder.itemView.req_vari_value.text=del_data.fleshvari
            viewHolder.itemView.req_quan_value.text=del_data.totalquan
            viewHolder.itemView.req_otp_value.text=del_data.flesherotp
            viewHolder.itemView.buyer_otp_value.text=del_data.buyerotp
            viewHolder.itemView.flesher_mobile_value.text=del_data.flesherphone
            viewHolder.itemView.buyer_mobile_value.text=del_data.buyermobileno
            viewHolder.itemView.price_value.text=del_data.totalprice
            viewHolder.itemView.ids_value.text=del_data.orderid
        }

    }
}