package com.ums.teslasautomation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_power.*
import kotlinx.android.synthetic.main.activity_temp.*
import kotlinx.android.synthetic.main.power_view.view.*
import kotlinx.android.synthetic.main.temp_view.view.*

class PowerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_power)
        search_btn.setOnClickListener {
            val extract_powerdate=search_date.text.toString()
            retrieve_data(extract_powerdate)
        }
    }

    private fun retrieve_data(extractDate: String) {
        val ref= FirebaseDatabase.getInstance().getReference("/V_I_LOG/$extractDate")
        ref.addValueEventListener(object : ValueEventListener {
            val power_adapter= GroupAdapter<ViewHolder>()
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                for(h in p0.children)
                {
                    val get_power=h.getValue(power_save::class.java)
                    if(get_power==null)
                        return
                    else{
                        power_adapter.add(power_item(get_power))
                    }
                }
                power_recycle.adapter=power_adapter
            }

        })
    }
    class power_item(val powergets:power_save): Item<ViewHolder>()
    {
        override fun getLayout(): Int {
            return R.layout.power_view
        }

        override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.itemView.volt_value.text=powergets.voltage
            viewHolder.itemView.current_value.text=powergets.current
            viewHolder.itemView.pwtime_value.text=powergets.time
        }

    }
    class power_save(val voltage:String,val current:String,val time:String)
    {
        constructor() : this("","","")
    }
}