package com.ums.tesfood.Authentication

class sign_up_class(var full_name:String,
                     var phone:String,
                     var  email:String,
                    var pass:String,
                    var conf_pass:String,
                    var district:String,
                    var city:String,
                    val shopname:String,val photo:String)
{
    constructor() : this("","","","","","","","","")
}