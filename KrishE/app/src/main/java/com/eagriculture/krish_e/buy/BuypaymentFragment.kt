package com.eagriculture.krish_e.buy

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.eagriculture.krish_e.R

class BuypaymentFragment : Fragment() {
    lateinit var paybtn:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_buypayment, container, false)
        paybtn=view.findViewById(R.id.paybtn)

        return view
    }


}