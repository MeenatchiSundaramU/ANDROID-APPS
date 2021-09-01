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
import kotlinx.android.synthetic.main.activity_temp.*
import kotlinx.android.synthetic.main.temp_view.view.*

class TempActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temp)
        searchtemp_btn.setOnClickListener {
            val extract_date=searchtemp_date.text.toString()
            retrieve_data(extract_date)
        }
    }

    private fun retrieve_data(extractDate: String) {
       val ref=FirebaseDatabase.getInstance().getReference("/TemperatureRecord/$extractDate")
        ref.addValueEventListener(object :ValueEventListener{
            val tem_adapter=GroupAdapter<ViewHolder>()
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                 for(h in p0.children)
                 {
                     val get_temp=h.getValue(temp_save::class.java)
                     if(get_temp==null)
                         return
                     else{
                         tem_adapter.add(temp_item(get_temp))
                     }
                 }
                temp_recycle.adapter=tem_adapter
            }

        })
    }
    class temp_item(val gets:temp_save):Item<ViewHolder>()
    {
        override fun getLayout(): Int {
            return R.layout.temp_view
        }

        override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.itemView.temp_value.text=gets.temp
            viewHolder.itemView.time_value.text=gets.time
        }

    }
    class temp_save(val temp:String,val time:String)
    {
        constructor() : this("","")
    }
}