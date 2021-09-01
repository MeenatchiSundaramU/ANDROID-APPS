package com.eagriculture.krish_e.sell

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eagriculture.krish_e.R


class EsellselledproductsFragment : Fragment() {
   lateinit var selledproductsrecyclerview:RecyclerView
    lateinit var eselllayout:RecyclerView.LayoutManager
    lateinit var selledAdapter: SelledAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_esellselledproducts, container, false)
        var selledlist= arrayListOf<SelledProduct>(
            SelledProduct(
                R.drawable.common_dp,
                "Tomato",
                "400",
                "4800",
                "25/01/2020",
                "1233 456 1233",
                "5"
            )
        ,
            SelledProduct(
                R.drawable.common_dp,
                "Potato",
                "200",
                "3600",
                "25/02/2020",
                "7895 456 4562",
                "3.5"
            ),
            SelledProduct(
                R.drawable.common_dp,
                "Brinjal",
                "150",
                "1350",
                "25/03/2020",
                "6789 456 1233",
                "4.5"
            )


        )
        selledproductsrecyclerview=view.findViewById(R.id.selledproductsrecyclerview)
        eselllayout=LinearLayoutManager(activity)
        selledAdapter= SelledAdapter(
            activity as Context,
            selledlist
        )
        selledproductsrecyclerview.adapter=selledAdapter
        selledproductsrecyclerview.layoutManager=eselllayout
        selledproductsrecyclerview.addItemDecoration(DividerItemDecoration(selledproductsrecyclerview.context,(eselllayout as LinearLayoutManager).orientation))

        return view
    }




}