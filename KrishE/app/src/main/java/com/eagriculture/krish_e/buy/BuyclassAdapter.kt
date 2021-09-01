package com.eagriculture.krish_e.buy

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eagriculture.krish_e.R
import kotlinx.android.synthetic.main.buyproducts_view.view.*

class BuyclassAdapter(var context:Activity,var buylist:ArrayList<buyclass>):RecyclerView.Adapter<BuyclassAdapter.buyclassholder>() {
    class buyclassholder(buyview: View):RecyclerView.ViewHolder(buyview)
    {
        var buycropimage:ImageView=buyview.findViewById(R.id.buyproductsicon)
        var buycropname: TextView =buyview.findViewById(R.id.buycropname)
        var buyhigh:TextView=buyview.findViewById(R.id.buyhighvalue)
        var buymedium:TextView=buyview.findViewById(R.id.buymediumvalue)
        var buylow:TextView=buyview.findViewById(R.id.buylowvalue)
        var buybutton: Button =buyview.findViewById(R.id.buybutton1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): buyclassholder {
       var buyviews=LayoutInflater.from(parent.context).inflate(R.layout.buyproducts_view,parent,false)
        return buyclassholder(buyviews)

    }

    override fun getItemCount(): Int {
        return buylist.size
    }

    override fun onBindViewHolder(holder: buyclassholder, position: Int) {
       var buylist=buylist[position]
        holder.buycropimage.setImageResource(buylist.buyclassimage)
        holder.buycropname.text=buylist.buyclasscropname
        holder.buyhigh.text=buylist.buyclasshigh
        holder.buymedium.text=buylist.buyclassmedium
        holder.buylow.text=buylist.buyclasslow
        holder.buybutton.setOnClickListener {
            var buyconfind= Intent(context,BuypayActivity::class.java)
            buyconfind.putExtra("buycropimage",buylist.buyclassimage)
            buyconfind.putExtra("buycropname",buylist.buyclasscropname)
            buyconfind.putExtra("buycrophigh",buylist.buyclasshigh)
            buyconfind.putExtra("buycropmedium",buylist.buyclassmedium)
            buyconfind.putExtra("buycroplow",buylist.buyclasslow)
            context.startActivity(buyconfind)
        }
    }
}