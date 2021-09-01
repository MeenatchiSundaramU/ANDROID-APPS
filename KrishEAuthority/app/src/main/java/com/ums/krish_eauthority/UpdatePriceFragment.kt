package com.ums.krish_eauthority

import android.app.AlertDialog
import android.app.PendingIntent.getActivity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_update_price.*
import kotlinx.android.synthetic.main.text_view.view.*
import kotlinx.android.synthetic.main.update_layout.view.*


class UpdatePriceFragment : Fragment() {

lateinit var recycle: RecyclerView
    lateinit var progressBars:ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view=inflater.inflate(R.layout.fragment_update_price, container, false)
        progressBars=view.findViewById(R.id.updateprogressbar)
        recycle=view.findViewById(R.id.recyclerview)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        retrievedata()

    }

    private fun retrievedata() {
        val ref = FirebaseDatabase.getInstance().getReference("/addcrops")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                val adapters = GroupAdapter<ViewHolder>()
                for (h in p0.children) {
                    val s = h.getValue(updata::class.java)
                    if (s != null) {
                        adapters.add(User(s))
                    }
                }
               progressBars.visibility=View.GONE
                recycle.adapter = adapters


            }

        })

    }

    class User(val get: updata) : Item<ViewHolder>() {
        override fun getLayout(): Int {
            return R.layout.update_layout
        }

        override fun bind(viewHolder: ViewHolder, position: Int) {
            Picasso.get().load(get.uploadphotoss).into(viewHolder.itemView.updateimage)
            viewHolder.itemView.cropname.text = get.cropnames
            viewHolder.itemView.cropprice.text = get.baseprices
            viewHolder.itemView.marketpricevalue.text = get.marketprices
             viewHolder.itemView.updates.setOnClickListener {
                val ss=it
                val build=AlertDialog.Builder(it.context)
                build.setTitle("Update Price")
                val layout=LayoutInflater.from(it.context)
                val se=layout.inflate(R.layout.text_view,null)
                val baseprices=se.findViewById<EditText>(R.id.updatebaseprice)
                val marketprices=se.findViewById<EditText>(R.id.updatemarketprice)
                //val updatebtn=se.findViewById<Button>(R.id.updates)
                baseprices.setText(get.baseprices)
                marketprices.setText(get.marketprices)
                build.setView(se)

                build.setPositiveButton("Update"){_,_->
                    val ub=baseprices.text.toString()
                    val um=marketprices.text.toString()
                    val updaterefer=FirebaseDatabase.getInstance().getReference("/addcrops/${get.cropnames}")
                    val a=updata(get.cropnames,ub,um,get.uploadphotoss)
                    updaterefer.setValue(a).addOnSuccessListener {
                        Toast.makeText(ss.context,"Updated Successfully",Toast.LENGTH_SHORT).show()
                    }
                        .addOnFailureListener {
                            Toast.makeText(ss.context,it.message.toString(),Toast.LENGTH_SHORT).show()
                        }
                }
                build.setNegativeButton("Cancel"){_,_->

                }
                build.create()
                build.show()
            }
        }
        }


    }
