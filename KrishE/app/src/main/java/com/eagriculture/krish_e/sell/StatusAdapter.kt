package com.eagriculture.krish_e.sell

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eagriculture.krish_e.R

class StatusAdapter(var statuscontext:Context,var statusList:ArrayList<StatusData>):RecyclerView.Adapter <StatusAdapter.statusViewholder>(){
    class statusViewholder(view: View):RecyclerView.ViewHolder(view)
    {
        var statusImage:ImageView=view.findViewById(R.id.statusimage)
        var statuscropname:TextView=view.findViewById(R.id.statuscropname)
        var statusgetdate: TextView =view.findViewById(R.id.getcropsdatevalue)
        var statusexportdate:TextView=view.findViewById(R.id.exportscropsdatevalue)
        var statusmoneydate:TextView=view.findViewById(R.id.exportmoneydate)
        var  statusprogress1:TextView=view.findViewById(R.id.completed)
        var  statusprogress2:TextView=view.findViewById(R.id.Inprogress)
        var statusprogress3:TextView=view.findViewById(R.id.notstarted)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): statusViewholder {
        var statusview=LayoutInflater.from(parent.context).inflate(R.layout.status_view,parent,false)
        return statusViewholder(
            statusview
        )
    }

    override fun getItemCount(): Int {
    return statusList.size
    }

    override fun onBindViewHolder(holder: statusViewholder, position: Int) {
       var statuslist=statusList[position]
        holder.statusImage.setImageResource(statuslist.statuscrophoto)
        holder.statuscropname.text=statuslist.statuscropname
        holder.statusgetdate.text=statuslist.getcropsdate
        holder.statusexportdate.text=statuslist.exportcropsdate
        holder.statusprogress1.text=statuslist.progreess1
        holder.statusprogress2.text=statuslist.progress2
        holder.statusprogress3.text=statuslist.progress3
    }
}