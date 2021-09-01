package com.ums.krishedriver

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
class maplat (var lat1:Double,var long1:Double,var lat2:Double,var long2:Double):Parcelable{
    constructor() : this(0.0,0.0,0.0,0.0)
}