package com.ums.krish_eauthority

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Set(val hqqqaunt:String,val mqqqaunt:String,val lqqqaunt:String,val hqqprice:String,val mqqprice:String,val lqqprice:String):Parcelable{
    constructor() : this("","","","","","")
}