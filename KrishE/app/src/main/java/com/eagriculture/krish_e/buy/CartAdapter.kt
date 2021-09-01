package com.eagriculture.krish_e.buy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eagriculture.krish_e.R

class CartAdapter(var cartcontext: Context, var cartlist:ArrayList<CartData>):
    RecyclerView.Adapter<CartAdapter.cartholder>(){
    class cartholder(view: View): RecyclerView.ViewHolder(view)
    {
        var cartImage: ImageView =view.findViewById(R.id.cproductsicon)
        var ccropname: TextView =view.findViewById(R.id.ccropname)
        var chighquality: TextView =view.findViewById(R.id.chighvalue)
        var cmedium: TextView =view.findViewById(R.id.cmediumvalue)
        var clow: TextView =view.findViewById(R.id.clowvalue)
        var ctotal: TextView =view.findViewById(R.id.ctotalvalue)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cartholder {
        var cartview=
            LayoutInflater.from(parent.context).inflate(R.layout.cart_view,parent,false)
        return cartholder(cartview)
    }

    override fun getItemCount(): Int {
        return cartlist.size

    }

    override fun onBindViewHolder(holder: cartholder, position: Int) {
        var cartlist=cartlist[position]
        holder.cartImage.setImageResource(cartlist.cartImage)
        holder.ccropname.text=cartlist.cartcropname
        holder.chighquality.text=cartlist.cartcrophigh
        holder.cmedium.text=cartlist.cartcropmedium
        holder.clow.text=cartlist.cartcroplow
        holder.ctotal.text=cartlist.carttotal
    }
}