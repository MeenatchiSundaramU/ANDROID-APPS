package com.ums.tesdriver

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Delivery(val fleshtype:String,val fleshvari:String,val totalquan:String,val totalprice:String,val fleshername:String,val fshopname:String,val buyername:String,val buyermobileno:String,val orderid:String,val buyerlat:String,val buyerlong:String,val flesherphone:String,val flesherlat:String,val flesherlong: String,val flesherotp:String,val buyerotp:String):Parcelable
{
    constructor() : this("","","","","","","","","","","","","","","","")
}