package com.eagriculture.krish_e.sell

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eagriculture.krish_e.R

class SelledAdapter(var selledcontext:Context,var selledlist:ArrayList<SelledProduct>):RecyclerView.Adapter<SelledAdapter.selledviewholder>() {
    class selledviewholder(view: View):RecyclerView.ViewHolder(view)
    {
        var selledcropphoto:ImageView=view.findViewById(R.id.selledproductsicon)
        var selledcropnamevalue:TextView=view.findViewById(R.id.selledcropname)
        var selledcropquantityvalue:TextView=view.findViewById(R.id.selledcropquantityvalue)
        var selledcropamountvalue:TextView=view.findViewById(R.id.selledcropamountvalue)
        var selledcroptransvalue:TextView=view.findViewById(R.id.selledtransactionvalue)
        var selledcropratingvalue:TextView=view.findViewById(R.id.selledratingvalue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): selledviewholder {
       var selledview=LayoutInflater.from(parent.context).inflate(R.layout.selledproducts_view,parent,false)
        return selledviewholder(
            selledview
        )
    }

    override fun getItemCount(): Int {
        return selledlist.size
    }

    override fun onBindViewHolder(holder: selledviewholder, position: Int) {
        var selledlist=selledlist[position]
        holder.selledcropphoto.setImageResource(selledlist.selledcrophoto)
        holder.selledcropnamevalue.text=selledlist.selledcropname
        holder.selledcropquantityvalue.text=selledlist.selledcropquantity
        holder.selledcropamountvalue.text=selledlist.selledcropamount
        holder.selledcroptransvalue.text=selledlist.selledcroptransaction
        holder.selledcropratingvalue.text=selledlist.selledrating
    }
}