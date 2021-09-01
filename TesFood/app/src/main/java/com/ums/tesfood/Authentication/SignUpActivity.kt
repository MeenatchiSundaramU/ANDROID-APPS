package com.ums.tesfood.Authentication

import HomeActivity.FleshersSplashActivity
import androidx.appcompat.app.AppCompatActivity
import HomeActivity.HomeActivity
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.ums.tesfood.R
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.fragment_add_product.view.*
import java.io.ByteArrayOutputStream
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity(){
    lateinit var full_name:String
    var datas: Uri?=null
    var cameraimage:Bitmap?=null
    lateinit var phone:String
    lateinit var  email:String
    lateinit var pass:String
    lateinit var conf_pass:String
    lateinit var district:String
    lateinit var city:String
    lateinit var shopname: String
    var chickenans:String?=null
    var muttonans:String?=null
    var fishans:String?=null
    var kaadaians:String?=null
    var vaanans:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar!!.hide()
        var flesh_districts= arrayOf("Erode","Tirunelveli")
        var flesh_citys=arrayOf("Bhavani","Vallioor")
        flesh_district.adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,flesh_districts)
        flesh_city.adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,flesh_citys)
        flesh_district.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                district=flesh_districts.get(p2)
            }
        }
        flesh_city.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                city=flesh_citys.get(p2)
            }
        }
        regphoto.setOnClickListener {
            val build= AlertDialog.Builder(this)
            build.setTitle("Image Selection")
            build.setMessage("Choose the Mode of Photo Selection")
            build.setPositiveButton("Gallery"){ _,_->
                usinggallery()
            }
            build.setNegativeButton("Camera"){_,_->
                usingcamera()
            }
            build.create()
            build.show()
        }
        sign_in.setOnClickListener {
            startActivity(Intent(this,SignInActivity::class.java))
            finish()
        }
        createuser.setOnClickListener {

            createUser()
        }
    }
    private fun createUser()
    {
        full_name=signup_fullname.text.toString()
        phone=signup_phone.text.toString()
        email=signup_email.text.toString()
        pass=signup_pass.text.toString()
        conf_pass=signup_conf_pass.text.toString()
        shopname=signup_shopname.text.toString()
        if(fish.isChecked)
        {
            fishans="Fish"
        }
        if(mutton.isChecked)
        {
            muttonans="Mutton"
        }
        if(chicken.isChecked)
        {
            chickenans="Chicken"
        }
        if(kaadai.isChecked)
        {
            kaadaians="Kaadai"
        }
        if(vaankolzhi.isChecked)
        {
            vaanans="VaanKolzhi"
        }
        when{
            TextUtils.isEmpty(full_name)->{
                signup_fullname.error="Full Name is Required"
                signup_fullname.requestFocus()
            }
            TextUtils.isEmpty(phone)->{
                signup_phone.error="Phone Number is Required"
                signup_phone.requestFocus()
            }
            TextUtils.isEmpty(email)->{
                signup_email.error="Email is Required"
                signup_email.requestFocus()
            }
            TextUtils.isEmpty(pass)->{
                signup_pass.error="Password is Required"
                signup_pass.requestFocus()
            }
            TextUtils.isEmpty(conf_pass)->{
                signup_conf_pass.error="Confirm Password is Required"
                signup_conf_pass.requestFocus()
            }
            TextUtils.isEmpty(shopname)->{
                signup_shopname.error="Shop Name is Required"
                signup_shopname.requestFocus()
            }
            !TextUtils.equals(pass,conf_pass)->{
                signup_pass.error="Password and Confirm not matched"
                signup_pass.requestFocus()
                signup_conf_pass.error="Password and Confirm Password not matched"
                signup_conf_pass.requestFocus()
            }
            else->{
                sign_up_progress.visibility=View.VISIBLE
                val signup_auth= FirebaseAuth.getInstance()
                signup_auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                    if(it.isSuccessful)
                    {
                            uploadimage(it.result.user.uid)
                    }
                    else
                    {
                        sign_up_progress.visibility=View.INVISIBLE
                        Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                        signup_auth.signOut()
                    }
                }
            }
        }
    }

    private fun signup_upload(uid: String,photo: String)
    {
        val signup_ref=FirebaseDatabase.getInstance().getReference("/FlesherAccount/$uid")
        val allshops=FirebaseDatabase.getInstance().getReference("/AllShops/$district/$uid")
        val fish_ref=FirebaseDatabase.getInstance().getReference("/Flesher_Details/Fish/$district/$city/$uid")
        val mutton_ref=FirebaseDatabase.getInstance().getReference("/Flesher_Details/Mutton/$district/$city/$uid")
        val chicken_ref=FirebaseDatabase.getInstance().getReference("/Flesher_Details/Chicken/$district/$city/$uid")
        val kaadai_ref=FirebaseDatabase.getInstance().getReference("/Flesher_Details/Kaadai/$district/$city/$uid")
        val vaan_ref=FirebaseDatabase.getInstance().getReference("/Flesher_Details/VaanKolzhi/$district/$city/$uid")
        if(chickenans!=null)
        {
            chicken_ref.setValue(Flesh(uid,shopname,full_name,city,photo))
        }
        if(muttonans!=null)
        {
            mutton_ref.setValue(Flesh(uid,shopname,full_name,city,photo))
        }
        if(fishans!=null)
        {
            fish_ref.setValue(Flesh(uid,shopname,full_name,city,photo))
        }
        if(kaadaians!=null)
        {
            kaadai_ref.setValue(Flesh(uid,shopname,full_name,city,photo))
        }
        if(vaanans!=null)
        {
            vaan_ref.setValue(Flesh(uid,shopname,full_name,city,photo))
        }
        allshops.setValue(Flesh(uid,shopname,full_name,city,photo))
        signup_ref.setValue(sign_up_class(full_name,phone,email,pass, conf_pass, district, city,shopname,photo)).addOnCompleteListener {
            if(it.isSuccessful)
            {
                sign_up_progress.visibility=View.INVISIBLE
               Toast.makeText(this,"Data Saved Successfully",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,FleshersSplashActivity::class.java))
                finish()
            }
            else
            {
                sign_up_progress.visibility=View.INVISIBLE
                Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==0 ||requestCode==100 && resultCode== Activity.RESULT_OK && data!=null)
        {
            if(requestCode==0 && data!=null) {
                datas = data.data
                val bitmapconvert = MediaStore.Images.Media.getBitmap(contentResolver, datas)

                circularimage.setImageBitmap(bitmapconvert)
                regphoto.alpha=0f
            }
            else if(requestCode==100 && data!=null)
            {
                datas=data.data
                cameraimage= data.extras?.get("data") as Bitmap

                circularimage.setImageBitmap(cameraimage)
                regphoto.alpha=0f
            }
        }
        else return
    }
    private fun usinggallery()
    {
        val indphoto=Intent(Intent.ACTION_PICK)
        indphoto.type="image/+"
        startActivityForResult(indphoto,0)
    }
    private fun uploadimage(userid:String)
    {
        if(datas==null) {
            if(cameraimage==null) return
            else{
                val baos= ByteArrayOutputStream()
                val filename = userid
                val ref = FirebaseStorage.getInstance().getReference("/FlesherAccount/$filename")
                cameraimage!!.compress(Bitmap.CompressFormat.JPEG,100,baos)
                val imgbytes=baos.toByteArray()
                ref.putBytes(imgbytes).addOnSuccessListener {
                    Toast.makeText(this,"Camera Photo Saved Suucessfully",Toast.LENGTH_SHORT).show()
                    ref.downloadUrl.addOnSuccessListener {
                        Log.d("checkings", "The photo added successfully $it")
                        signup_upload(userid,it.toString())
                    }
                }
            }
        }
        else{
            val filename = userid
            val ref = FirebaseStorage.getInstance().getReference("/FlesherAccount/$filename")
            ref.putFile(datas!!).addOnSuccessListener {
                Log.d("checkings", "The photo added successfully${it.metadata?.path}")
                ref.downloadUrl.addOnSuccessListener {
                    it.toString()
                    Log.d("checkings", "The photo added successfully $it")
                    signup_upload(userid,it.toString())
                }
            }
        }
    }
    private fun usingcamera()
    {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
                out->out.resolveActivity(this.packageManager!!)?.also {
            startActivityForResult(out,100)
        }
        }
    }

    class Flesh(val uid: String,val shopname:String,val ownername:String,val city:String,val photo:String)
    {
        constructor() : this("","","","","")
    }
}