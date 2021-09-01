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


class EsellmarketpriceFragment : Fragment() {
    lateinit var marketRecyclerview:RecyclerView
    lateinit var marketlayoutManager:RecyclerView.LayoutManager
    lateinit var marketAdapter: MarketAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_esellmarketprice, container, false)
        var marketdata= arrayListOf<MarketPrice>(
            MarketPrice(
                R.drawable.common_dp,
                "Tomato",
                "1KG",
                "3",
                "6.75-13.5",
                "6.07-12.5",
                "4.25-8.51"
            ), MarketPrice(
                R.drawable.common_dp,
                "Potato",
                "1KG",
                "3",
                "9.25-18.5",
                "8.32-16.65",
                "5.82-11.65"
            ), MarketPrice(
                R.drawable.common_dp,
                "Brinjal",
                "1KG",
                "3",
                "4.25-8.5",
                "3.83-7.65",
                "3-5.36"
            )
        )
       marketRecyclerview=view.findViewById(R.id.marketpricerecycleview)
        marketlayoutManager=LinearLayoutManager(activity)
        marketRecyclerview.addItemDecoration(DividerItemDecoration(marketRecyclerview.context,(marketlayoutManager as LinearLayoutManager).orientation))
        marketAdapter= MarketAdapter(
            activity as Context,
            marketdata
        )
        marketRecyclerview.adapter=marketAdapter
        marketRecyclerview.layoutManager=marketlayoutManager

        return view

    }


}