package com.eagriculture.krish_e.buy

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eagriculture.krish_e.R


class BuycartFragment : Fragment() {
    lateinit var cartRecyclerView: RecyclerView
    lateinit var cartLayoutManager: RecyclerView.LayoutManager
    lateinit var cartSharedPreferences: SharedPreferences
    lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_buycart, container, false)
        var cartlist= arrayListOf<CartData>(
            CartData(R.drawable.common_dp,"Tomato","200","100","50","350")

        )
        cartRecyclerView=view.findViewById(R.id.cartrecycleview)
        cartLayoutManager= LinearLayoutManager(activity)
        cartSharedPreferences=getContext()!!.getSharedPreferences(getString(R.string.reg_pass),
            Context.MODE_PRIVATE)
        var cropname=cartSharedPreferences.getString("cartcropname","mano")
        var carthighquality=cartSharedPreferences.getString("carthighquant","mano")
        var cartmediumquality=cartSharedPreferences.getString("cartmedquant","mano")
        var cartlowquality=cartSharedPreferences.getString("cartlowquant","mano")
        var carttotal=cartSharedPreferences.getString("carttotal","mano")
        if(cropname!="mano") {
            cartlist.add(
                CartData(
                    R.drawable.common_dp,
                    cropname!!,
                    carthighquality!!,
                    cartmediumquality!!,
                    cartlowquality!!,
                    carttotal!!
                )
            )
        }
        cartAdapter= CartAdapter(activity as Context,cartlist)
        cartRecyclerView.adapter=cartAdapter
        cartRecyclerView.layoutManager=cartLayoutManager
        cartRecyclerView.addItemDecoration(
            DividerItemDecoration(cartRecyclerView.context,(cartLayoutManager as LinearLayoutManager).orientation)
        )

        return view
    }


}