package com.eagriculture.krish_e.sell

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eagriculture.krish_e.R

class CancelAdapter(var cancelcontext:Context,var cancellist:ArrayList<CancelProducts>):RecyclerView.Adapter<CancelAdapter.cancelholder>(){
    class cancelholder(view: View):RecyclerView.ViewHolder(view)
    {
        var cancelImage:ImageView=view.findViewById(R.id.selledproductsicon)
        var cancelcropname:TextView=view.findViewById(R.id.cancelcropname)
        var cancelcropquantity:TextView=view.findViewById(R.id.cancelquantityvalue)
        var cancelhighquality:TextView=view.findViewById(R.id.cancellhighvalue)
        var cancelmedium:TextView=view.findViewById(R.id.cancellmediumvalue)
        var cancellow:TextView=view.findViewById(R.id.cancelllowvalue)
        var canceldate:TextView=view.findViewById(R.id.canceladdproductdatevalue)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cancelholder {
        var cancelview=LayoutInflater.from(parent.context).inflate(R.layout.cancel_menuview,parent,false)
        return cancelholder(
            cancelview
        )
    }

    override fun getItemCount(): Int {
        return cancellist.size

    }

    override fun onBindViewHolder(holder: cancelholder, position: Int) {
        var cancellist=cancellist[position]
        cancellist.cancelImage?.let { holder.cancelImage.setImageResource(it) }
        holder.cancelcropname.text=cancellist.cancelcropname
        holder.cancelcropquantity.text=cancellist.cancelcropquantity
        holder.cancelhighquality.text=cancellist.cancelcrophigh
        holder.cancelmedium.text=cancellist.cancelcropmedium
        holder.cancellow.text=cancellist.cancelcroplow
        holder.canceldate.text=cancellist.canceldate
    }
}