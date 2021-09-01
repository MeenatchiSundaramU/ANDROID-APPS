package com.ums.krish_eauthority

import android.content.Intent
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
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.buyer_view.view.*

class FarmerssettlementFragment : Fragment() {
    lateinit var settrecycle:RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_farmerssettlement, container, false)
        settrecycle=view.findViewById(R.id.settlemainrecycle)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retrievedatas()
    }
    private fun retrievedatas()
    {
        val ref= FirebaseDatabase.getInstance().getReference("/addcrops")
        ref.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                val group= GroupAdapter<ViewHolder>()
                for(h in p0.children){
                    val get=h.getValue(updata::class.java)
                    if(get==null)return
                    group.add(settleshow(get))
                }
                group.setOnItemClickListener { item, view ->
                    val type=item as settleshow
                    val ind=Intent(view.context,SettleNextActivity::class.java)
                    ind.putExtra("KEYSS",type.gets.cropnames)
                    startActivity(ind)
                }
                settrecycle.adapter=group
            }

        })
    }
    class settleshow(val gets:updata): Item<ViewHolder>()
    {
        override fun getLayout(): Int {
            return R.layout.buyer_view
        }

        override fun bind(viewHolder: ViewHolder, position: Int) {
            Picasso.get().load(gets.uploadphotoss).into(viewHolder.itemView.buyershowcropimage)
            viewHolder.itemView.buyershowcropname.text=gets.cropnames
        }

    }
}