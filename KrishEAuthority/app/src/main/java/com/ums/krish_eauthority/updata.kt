package com.ums.krish_eauthority

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
class updata(val cropnames:String,val baseprices:String,val marketprices:String,val uploadphotoss: String):Parcelable
{
    constructor() : this("","","","")
}