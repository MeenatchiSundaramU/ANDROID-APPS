package com.eagriculture.krish_e.buy

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eagriculture.krish_e.sell.EbuyMarketadapter
import com.eagriculture.krish_e.R


class BuymarketpriceFragment : Fragment() {
    lateinit var buymarketview:RecyclerView
    lateinit var buylayout:RecyclerView.LayoutManager
    lateinit var buymarketAdapter: EbuyMarketadapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view=inflater.inflate(R.layout.fragment_buymarketprice, container, false)

        var marketdata= arrayListOf<EbuyMarketprice>(
            EbuyMarketprice(R.drawable.common_dp,"Tomato","1KG","30","18.23","16.4","13.12"
            ), EbuyMarketprice(R.drawable.common_dp,"Potato","1KG","40","24.98","22.48"," 17.98"
            ), EbuyMarketprice(R.drawable.common_dp,"Brinjal","1KG","20","11.48","10.33","8.26"
            )

        )
        buymarketview=view.findViewById(R.id.ebuymarketpricerecycleview)
        buylayout= LinearLayoutManager(activity)
        buymarketview.addItemDecoration(DividerItemDecoration(buymarketview.context,(buylayout as LinearLayoutManager).orientation))
        buymarketAdapter= EbuyMarketadapter(
            activity as Context,
            marketdata
        )
        buymarketview.adapter=buymarketAdapter
        buymarketview.layoutManager=buylayout
        return view
          }



}