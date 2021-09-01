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
import com.eagriculture.krish_e.R

class TrackorderFragment : Fragment() {
    lateinit var trackRecyclerView: RecyclerView
    lateinit var tracklayoutManager: RecyclerView.LayoutManager
    lateinit var trackAdapter: TrackAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_trackorder, container, false)
        var tracklist= arrayListOf<TrackData>(
            TrackData(R.drawable.common_dp,"Tomato","29/03/2020","30/03/2020","01/04/2020","Not Started","Not Started","Not Started"),
            TrackData(R.drawable.common_dp,"Potato","03/03/2020","04/03/2020","06/03/2020","Completed","In Progress","Not Started"),
            TrackData(R.drawable.common_dp,"Carrot","12/02/2020","13/02/2020","14/02/2020","Completed","Completed","Completed"),
            TrackData(R.drawable.common_dp,"Onion","04/02/2020","05/02/2020","06/02/2020","Completed","Completed","In Progress"),
            TrackData(R.drawable.common_dp,"Ginger","29/01/2020","30/01/2020","1/02/2020","In Progress","Not Started","Not Started")

        )
        trackRecyclerView=view.findViewById(R.id.trackrecyclerview)
        tracklayoutManager= LinearLayoutManager(activity)
        trackAdapter= TrackAdapter(activity as Context,tracklist)
        trackRecyclerView.adapter=trackAdapter
        trackRecyclerView.layoutManager=tracklayoutManager
        trackRecyclerView.addItemDecoration(
            DividerItemDecoration(
            trackRecyclerView.context,(tracklayoutManager as LinearLayoutManager).orientation
        )
        )
        return view
    }


}