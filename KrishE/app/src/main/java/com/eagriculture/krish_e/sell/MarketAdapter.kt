package com.eagriculture.krish_e.sell

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eagriculture.krish_e.R

class MarketAdapter(context: Context,var marketPrice:ArrayList<MarketPrice>):RecyclerView.Adapter<MarketAdapter.marketviewholder>() {
    class marketviewholder(view: View):RecyclerView.ViewHolder(view)
    {
        var marketimageview:ImageView=view.findViewById(R.id.esellmarketcropimage)
        var marketcropname:TextView=view.findViewById(R.id.esellmarketcropname)

        var marketquantityvalue:TextView=view.findViewById(R.id.esellquantityvalue)

        var Basepricevalue:TextView=view.findViewById(R.id.esellmarketpricevalue)
        var esellhighprice:TextView=view.findViewById(R.id.esellhighqualityvalue)
        var esellmediumprice:TextView=view.findViewById(R.id.esellmediumqualityvalue)
        var eselllowprice:TextView=view.findViewById(R.id.eselllowqualityvalue)
        //var marketmoredetails:Button=view.findViewById(R.id.moredetailsbtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): marketviewholder {
       var marketview=LayoutInflater.from(parent.context).inflate(R.layout.marketprice_view,parent,false)
        return marketviewholder(
            marketview
        )
    }

    override fun getItemCount(): Int {
        return marketPrice.size
    }

    override fun onBindViewHolder(holder: marketviewholder, position: Int) {
        var marketpricelist=marketPrice[position]
       holder.marketimageview.setImageResource(marketpricelist.esellmarketimageview)
        holder.marketcropname.text=marketpricelist.esellmarketcropname
        holder.marketquantityvalue.text=marketpricelist.esellmarketquantityvalue
        holder.Basepricevalue.text=marketpricelist.basevalue
        holder.esellhighprice.text=marketpricelist.esellhighvalue
        holder.esellmediumprice.text=marketpricelist.esellmediumvalue
        holder.eselllowprice.text=marketpricelist.eselllowvalue

    }
}