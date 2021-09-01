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

class EsellstatusFragment : Fragment() {
  lateinit var statusRecyclerView: RecyclerView
    lateinit var statuslayoutManager:RecyclerView.LayoutManager
    lateinit var statusAdapter: StatusAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_esellstatus, container, false)
        var statuslist= arrayListOf<StatusData>(
            StatusData(
                R.drawable.common_dp,
                "Tomato",
                "29/03/2020",
                "30/03/2020",
                "01/04/2020",
                "Not Started",
                "Not Started",
                "Not Started"
            ),
            StatusData(
                R.drawable.common_dp,
                "Potato",
                "03/03/2020",
                "04/03/2020",
                "06/03/2020",
                "Completed",
                "In Progress",
                "Not Started"
            ),
            StatusData(
                R.drawable.common_dp,
                "Carrot",
                "12/02/2020",
                "13/02/2020",
                "14/02/2020",
                "Completed",
                "Completed",
                "Completed"
            ),
            StatusData(
                R.drawable.common_dp,
                "Onion",
                "04/02/2020",
                "05/02/2020",
                "06/02/2020",
                "Completed",
                "Completed",
                "In Progress"
            ),
            StatusData(
                R.drawable.common_dp,
                "Ginger",
                "29/01/2020",
                "30/01/2020",
                "1/02/2020",
                "In Progress",
                "Not Started",
                "Not Started"
            )

        )
        statusRecyclerView=view.findViewById(R.id.statusrecyclerview)
        statuslayoutManager=LinearLayoutManager(activity)
        statusAdapter= StatusAdapter(
            activity as Context,
            statuslist
        )
        statusRecyclerView.adapter=statusAdapter
        statusRecyclerView.layoutManager=statuslayoutManager
        statusRecyclerView.addItemDecoration(DividerItemDecoration(
            statusRecyclerView.context,(statuslayoutManager as LinearLayoutManager).orientation
        ))

        return view
    }


}