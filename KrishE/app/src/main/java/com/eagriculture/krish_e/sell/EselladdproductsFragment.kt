package com.eagriculture.krish_e.sell

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.eagriculture.krish_e.R
import kotlin.math.round


class EselladdproductsFragment : Fragment() {
    lateinit var addcropname: EditText
    lateinit var addcropvariety: EditText
    lateinit var addcropharvestdate: EditText
    lateinit var addtodaysdate: EditText
    lateinit var addcropquantity: EditText
    lateinit var addhighquality: EditText
    lateinit var addmediumquality: EditText
    lateinit var addlowquality: EditText
    lateinit var suggestbtn: Button
    lateinit var addhighvalue: TextView
    lateinit var addmediumvalue: TextView
    lateinit var addlowvalue: TextView
    lateinit var addproductbtn: Button
    lateinit var addSharedPreferences: SharedPreferences
    var tomatobasePrice: Float = 3.0f
    var brinjalbasePrice: Float = 2.5f
    var tomatomarketPrice: Float = 30.0f
    var brinjalmarketPrice: Float = 25.0f
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_eselladdproducts, container, false)
        addSharedPreferences =
            getContext()!!.getSharedPreferences(getString(R.string.reg_pass), MODE_PRIVATE)
        addSharedPreferences.edit().putFloat("TomatoBasePrice", tomatobasePrice).apply()
        addSharedPreferences.edit().putFloat("BrinjalBasePrice", brinjalbasePrice).apply()
        addSharedPreferences.edit().putFloat("TomatoMarketPrice", tomatomarketPrice).apply()
        addSharedPreferences.edit().putFloat("BrinjalMarketPrice", brinjalmarketPrice).apply()
        var tomatoBasePrice=addSharedPreferences.getFloat("TomatoBasePrice",10.0f)
        var tomatoMarketPrice=addSharedPreferences.getFloat("TomatoMarketPrice",10.0f)
        var brinjalbasePrice=addSharedPreferences.getFloat("BrinjalBasePrice",10.0f)
        var brinjalmarketPrice=addSharedPreferences.getFloat("BrinjalMarketPrice",10.0f)
        addcropname = view.findViewById(R.id.addcropname)
        addcropvariety = view.findViewById(R.id.addcropvariety)
        addcropharvestdate = view.findViewById(R.id.addharvestdate)
        addtodaysdate = view.findViewById(R.id.addtodaydate)
        addcropquantity = view.findViewById(R.id.addcropquantity)
        addhighquality = view.findViewById(R.id.addhighqualityquantity)
        addlowquality = view.findViewById(R.id.addlowqualityquantity)
        addmediumquality = view.findViewById(R.id.addmediumqualityquantity)
        suggestbtn = view.findViewById(R.id.addsuggestion)
        addhighvalue = view.findViewById(R.id.highqualitypricevalue)
        addmediumvalue = view.findViewById(R.id.mediumqualitypricevalue)
        addlowvalue = view.findViewById(R.id.lowqualitypricevalue)
        addproductbtn = view.findViewById(R.id.addproductbtn)
        suggestbtn.setOnClickListener {
            if (addcropname.text.toString().equals("Tomato") || addcropname.text.toString().equals("tomato")) {
                checksuggest(tomatoBasePrice,tomatoMarketPrice)
            }
            else if(addcropname.text.toString().equals("Brinjal") || addcropname.text.toString().equals("brinjal"))
            {
                checksuggest(brinjalbasePrice,brinjalmarketPrice)
            }
            else
            {
                Toast.makeText(context,"Enter correct crop name",Toast.LENGTH_SHORT).show()
            }
            addproductbtn.setOnClickListener {
                var cropname: String = addcropname.text.toString()
                var cropquantity: String = addcropquantity.text.toString()
                var crophighquality: String = addhighquality.text.toString()
                var cropmediumquality: String = addmediumquality.text.toString()
                var croplowquality: String = addlowquality.text.toString()
                var cropdate: String = addtodaysdate.text.toString()
                addSharedPreferences.edit().putString("cropname", cropname).apply()
                addSharedPreferences.edit().putString("cropquantity", cropquantity).apply()
                addSharedPreferences.edit().putString("crophighquality", crophighquality).apply()
                addSharedPreferences.edit().putString("cropmediumquality", cropmediumquality)
                    .apply()
                addSharedPreferences.edit().putString("croplowquality", croplowquality).apply()
                addSharedPreferences.edit().putString("date", cropdate).apply()
                var addproduct = Intent(context, AddproductssplashActivity::class.java)
                startActivity(addproduct)

            }


        }




        return view
    }
    @SuppressLint("SetTextI18n")
    fun checksuggest(Baseprice:Float, MarketPrice:Float)
    {
        var baseprice:Double=Baseprice.toDouble()
        var marketprice:Double=MarketPrice.toDouble()
        var diff:Double=marketprice-baseprice
        var highquality1:String
        var highquality2:String
        var mediumquality1:String
        var mediumquality2:String
        var lowquality1:String
        var lowquality2:String
        var hqlow=0.25*diff
        var hqhigh=0.5*diff
        if(hqhigh<baseprice){
            hqlow=baseprice
            hqhigh=baseprice
        }
        highquality1= round(hqlow).toString()
        highquality2=round(hqhigh).toString()
        var medlow=0.25*diff*0.9
        var medhigh=0.5*diff*0.9
        if(medhigh<baseprice){
            medhigh=baseprice
            medlow=baseprice
        }
        mediumquality1=round(medlow).toString()
        mediumquality2=round(medhigh).toString()
        var lowlow=0.25*diff*0.9*0.7
        var lowhigh=0.5*diff*0.9*0.7
        if(lowhigh<baseprice){
            lowhigh=baseprice
            lowlow=baseprice
        }
        lowquality1=round(lowlow).toString()
        lowquality2=round(lowhigh).toString()
        addhighvalue.text= "$highquality1-$highquality2"
        addlowvalue.text="$lowquality1-$lowquality2"
        addmediumvalue.text="$mediumquality1-$mediumquality2"
    }

}