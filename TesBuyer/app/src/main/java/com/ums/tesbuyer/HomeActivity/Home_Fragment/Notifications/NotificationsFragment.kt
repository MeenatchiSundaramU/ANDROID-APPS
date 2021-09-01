package com.ums.tesbuyer.HomeActivity.Home_Fragment.Notifications

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ums.tesbuyer.HomeActivity.Home_Fragment.OfferFragment.Companion.uids
import com.ums.tesbuyer.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.notifi_view.view.*
import java.text.SimpleDateFormat
import java.util.*


class RequirementFragment : Fragment() {
lateinit var notifi_recycle:RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_requirement, container, false)
        notifi_recycle=view.findViewById(R.id.notifi_recycle)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notifi_data()
    }

    @SuppressLint("SimpleDateFormat")
    private fun notifi_data() {
        val calen= Calendar.getInstance()
        val date= SimpleDateFormat("dd-MM-y").format(calen.time).toString()
        val req_ref= FirebaseDatabase.getInstance().getReference("/BuyerOrder/$uids/$date")
        req_ref.addValueEventListener(object : ValueEventListener {
            val req_adap= GroupAdapter<ViewHolder>()
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                for(h in p0.children) {
                    val get_datas = h.getValue(Notifications::class.java)
                    if(get_datas!=null)
                    {
                        req_adap.add(ReqAdapter(get_datas))
                    }
                }
                notifi_recycle.adapter=req_adap
            }

        })
    }




    class ReqAdapter(val req:Notifications): Item<ViewHolder>()
    {
        override fun getLayout(): Int {
            return R.layout.notifi_view
        }

        override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.itemView.req_flesh_name.text=req.fleshtype
            viewHolder.itemView.req_vari_value.text=req.fleshvari
            viewHolder.itemView.req_quan_value.text=req.totalquan
            viewHolder.itemView.req_otp_value.text=req.otp
            viewHolder.itemView.id_value.text=req.orderid
viewHolder.itemView.price_value.text=req.totalprice
        }

    }

}