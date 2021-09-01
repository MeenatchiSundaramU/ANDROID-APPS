package com.eagriculture.krish_e.sell

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eagriculture.krish_e.R
import com.eagriculture.krish_e.buy.EbuyMarketprice

class EbuyMarketadapter(var ebuycontext:Context,var buymarketlist:ArrayList<EbuyMarketprice>):RecyclerView.Adapter<EbuyMarketadapter.EbuymarketViewholder>() {
    class EbuymarketViewholder(buymarkview: View):RecyclerView.ViewHolder(buymarkview)
    {
        var ebuymarketimageview: ImageView =buymarkview.findViewById(R.id.ebuymarketcropimage)
        var ebuymarketcropname: TextView =buymarkview.findViewById(R.id.ebuymarketcropname)

        var ebuymarketquantityvalue: TextView =buymarkview.findViewById(R.id.ebuyquantityvalue)

        var ebuyBasepricevalue: TextView =buymarkview.findViewById(R.id.ebuymarketpricevalue)
        var ebuyhighprice: TextView =buymarkview.findViewById(R.id.ebuyhighqualityvalue)
        var ebuymediumprice: TextView =buymarkview.findViewById(R.id.ebuymediumqualityvalue)
        var ebuylowprice: TextView =buymarkview.findViewById(R.id.ebuylowqualityvalue)
       // var ebuymarketmoredetails: Button =buymarkview.findViewById(R.id.moredetailsbtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EbuymarketViewholder {
        var buymark=LayoutInflater.from(parent.context).inflate(R.layout.ebuy_marketview,parent,false)
        return EbuymarketViewholder(
            buymark
        )
    }

    override fun getItemCount(): Int {
        return buymarketlist.size
    }

    override fun onBindViewHolder(holder: EbuymarketViewholder, position: Int) {
        var ebuymarketpricelist=buymarketlist[position]
        holder.ebuymarketimageview.setImageResource(ebuymarketpricelist.ebuymarketimageview)
        holder.ebuymarketcropname.text=ebuymarketpricelist.ebuymarketcropname
        holder.ebuymarketquantityvalue.text=ebuymarketpricelist.ebuymarketquantityvalue
        holder.ebuyBasepricevalue.text=ebuymarketpricelist.marketvalue
        holder.ebuyhighprice.text=ebuymarketpricelist.ebuyhighvalue
        holder.ebuymediumprice.text=ebuymarketpricelist.ebuymediumvalue
        holder.ebuylowprice.text=ebuymarketpricelist.ebuylowvalue
    }
}