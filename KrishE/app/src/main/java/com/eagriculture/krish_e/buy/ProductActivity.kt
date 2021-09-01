package com.eagriculture.krish_e.buy

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eagriculture.krish_e.R

class ProductActivity : AppCompatActivity() {
    lateinit var buyviewrecycle:RecyclerView
    lateinit var buyviewlayout:RecyclerView.LayoutManager
    lateinit var buyviewAdapter:BuyclassAdapter
    lateinit var productviewtool:Toolbar
    lateinit var proviewshared:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_show)
        proviewshared=getSharedPreferences(getString(R.string.reg_pass),MODE_PRIVATE)
        var cropname=proviewshared.getString("searchedcrops","mano")
        buyviewrecycle=findViewById(R.id.productviewrecycler)
        productviewtool=findViewById(R.id.productviewtool)
        openToolbar()
        buyviewlayout=LinearLayoutManager(this)
        if(cropname.equals("Tomato")||cropname.equals("tomato")) {
            var buylist = arrayListOf<buyclass>(
                buyclass(R.drawable.common_dp, "Tomato", "19", "17", "14")
            )
            buyviewAdapter = BuyclassAdapter(this, buylist)
        }
        if(cropname.equals("Potato")||cropname.equals("potato"))
        {
            var buylist1= arrayListOf<buyclass>(buyclass(R.drawable.common_dp,"Potato","25","23","17"))
            buyviewAdapter= BuyclassAdapter(this,buylist1)
        }
        else if(cropname.equals("Brinjal")||cropname.equals("brinjal"))
        {
            var buylist2= arrayListOf<buyclass>(buyclass(R.drawable.common_dp,"Brinjal","12","11","9"))
            buyviewAdapter= BuyclassAdapter(this,buylist2)

        }

        buyviewrecycle.adapter=buyviewAdapter
        buyviewrecycle.layoutManager=buyviewlayout

    }
    fun openToolbar()
    {
        setSupportActionBar(productviewtool)
        supportActionBar?.title="Search Crops Results"
    }
}