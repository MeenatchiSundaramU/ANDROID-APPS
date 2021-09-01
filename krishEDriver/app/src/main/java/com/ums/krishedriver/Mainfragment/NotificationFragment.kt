package com.ums.krishedriver.Mainfragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ums.krishedriver.MapsActivity
import com.ums.krishedriver.R
import com.ums.krishedriver.maplat
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_notification.*



class NotificationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_notification, container, false)
     val accept:Button=view.findViewById(R.id.accept)
        val farebtn:Button=view.findViewById(R.id.farebtn)
      //  val notrecycle:RecyclerView=view.findViewById(R.id.notifi_recycle)
      //  val uid=FirebaseAuth.getInstance().currentUser!!.uid
      // val ref=FirebaseDatabase.getInstance().getReference("/BookingDetails/$uid")
       /* ref.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val adap=GroupAdapter<ViewHolder>()
                for(h in snapshot.children){
                    val ans=h.getValue(notificlass::class.java)
                    if(ans!=null)
                    {
                        adap.add(notifiadap(ans))
                    }
                }
                notrecycle.adapter=adap
            }

        })*/
        farebtn.setOnClickListener {
            val long1 = Math.toRadians(77.589302)
            val long2 = Math.toRadians(77.6105)
            val lat1 = Math.toRadians(8.392770)
            val lat2 = Math.toRadians(8.3926)

            val dlon: Double = long2 - long1
            val dlat: Double = lat2 - lat1
            val a = (Math.pow(Math.sin(dlat / 2), 2.0)
                    + (Math.cos(lat1) * Math.cos(lat2)
                    * Math.pow(Math.sin(dlon / 2), 2.0)))

            val c = 2 * Math.asin(Math.sqrt(a))
            val r = 6371.0
            totalfareval.text=(r*c*80).toInt().toString()
        }

       accept.setOnClickListener {
           val ind= Intent(context,MapsActivity::class.java)
            val gets:maplat= maplat(8.392770, 77.589302,8.3926,77.6105)
            ind.putExtra("GET",gets)
            startActivity(ind)
        }
        return view
    }
}
/*class notifiadap(val gets:notificlass):Item<ViewHolder>()
{
    override fun getLayout(): Int {
        return R.layout.notifi_view
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.farmersvalue.text=gets.name
        viewHolder.itemView.farmerslocvalue.text=gets.name
        viewHolder.itemView.complocvalue.text=gets.companylocation
        viewHolder.itemView.vegevalue.text=gets.vegname
        viewHolder.itemView.quantityvalue.text=gets.vegquantity
    }

}*/
/*
class notificlass(var companylocation:String,var latitude:String,var longitude:String,var name:String,
var uid:String,var vegname:String,var vegquantity:String,var clatitude:String,var clongitude:String)
{
    constructor() : this("","","","","","","","","")
}*/
