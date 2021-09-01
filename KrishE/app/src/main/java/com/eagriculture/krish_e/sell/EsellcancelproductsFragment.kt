package com.eagriculture.krish_e.sell

import android.content.Context
import android.content.Context.MODE_PRIVATE
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


class EsellcancelproductsFragment : Fragment() {
lateinit var cancelRecyclerView: RecyclerView
    lateinit var cancelLayoutManager: RecyclerView.LayoutManager
    lateinit var cancelSharedPreferences: SharedPreferences
    lateinit var cancelAdapter: CancelAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_esellcancelproducts, container, false)
       var cancellist= arrayListOf<CancelProducts>(
           CancelProducts(
               R.drawable.common_dp,
               "Tomato",
               "200",
               "100",
               "50",
               "50",
               "29/05/2020"
           )

       )
        cancelRecyclerView=view.findViewById(R.id.cancelRecyclerView)
       cancelLayoutManager=LinearLayoutManager(activity)
        cancelSharedPreferences=getContext()!!.getSharedPreferences(getString(R.string.reg_pass),MODE_PRIVATE)

        var cropname=cancelSharedPreferences.getString("cropname","mano")
       var cropquantity=cancelSharedPreferences.getString("cropquantity","mano")
        var crophighquality=cancelSharedPreferences.getString("crophighquality","mano")
        var cropmediumquality=cancelSharedPreferences.getString("cropmediumquality","mano")
        var croplowquality=cancelSharedPreferences.getString("croplowquality","mano")
        var cropdate=cancelSharedPreferences.getString("date","mano")
        if(cropname!="mano") {
            cancellist.add(
                CancelProducts(
                    R.drawable.common_dp,
                    cropname,
                    cropquantity,
                    crophighquality,
                    cropmediumquality,
                    croplowquality,
                    cropdate
                )
            )
        }
         cancelAdapter= CancelAdapter(
             activity as Context,
             cancellist
         )
        cancelRecyclerView.adapter=cancelAdapter
        cancelRecyclerView.layoutManager=cancelLayoutManager
        cancelRecyclerView.addItemDecoration(
            DividerItemDecoration(cancelRecyclerView.context,(cancelLayoutManager as LinearLayoutManager).orientation)
        )
        return view
    }


}