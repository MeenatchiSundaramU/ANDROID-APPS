package com.eagriculture.krish_e.buy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eagriculture.krish_e.R

class TrackAdapter(var trackcontext: Context, var trackList:ArrayList<TrackData>):
    RecyclerView.Adapter <TrackAdapter.trackviewholder>() {
    class trackviewholder(view: View) : RecyclerView.ViewHolder(view) {
        var tstatusImage: ImageView = view.findViewById(R.id.tstatusimage)
        var tstatuscropname: TextView = view.findViewById(R.id.tstatuscropname)
        var tstatusgetdate: TextView = view.findViewById(R.id.tgetcropsdatevalue)
        var tstatusexportdate: TextView = view.findViewById(R.id.texportscropsdatevalue)
       var tstatusdeliverdate: TextView = view.findViewById(R.id.texportmoneydate)
        var tstatusprogress1: TextView = view.findViewById(R.id.tcompleted)
        var tstatusprogress2: TextView = view.findViewById(R.id.tInprogress)
        var tstatusprogress3: TextView = view.findViewById(R.id.tnotstarted)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): trackviewholder {
        var trackview =
            LayoutInflater.from(parent.context).inflate(R.layout.buytrack_view, parent, false)
        return trackviewholder(trackview)
    }

    override fun getItemCount(): Int {
        return trackList.size
    }

    override fun onBindViewHolder(holder: trackviewholder, position: Int) {
        var tracklist = trackList[position]
        holder.tstatusImage.setImageResource(tracklist.statuscrophoto)
        holder.tstatuscropname.text = tracklist.statuscropname
        holder.tstatusgetdate.text = tracklist.getcropsdate
        holder.tstatusexportdate.text=tracklist.exportcropsdate
        holder.tstatusdeliverdate.text = tracklist.deliveredcropdate
        holder.tstatusprogress1.text = tracklist.progreess1
        holder.tstatusprogress2.text = tracklist.progress2
        holder.tstatusprogress3.text = tracklist.progress3
    }
}