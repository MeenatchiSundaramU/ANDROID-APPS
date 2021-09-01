package com.UMS.chatsapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Register(val uid:String,val name:String,val email:String,val pass:String,val photouri:String):Parcelable{
    constructor() : this("","","","","")
}