package com.ums.krishedriver.AuthenticationActivity

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.ums.krishedriver.R
import kotlinx.android.synthetic.main.activity_signup.*
import java.util.*

class SignupActivity : AppCompatActivity() {
    companion object{
        var photodata:Uri?=null
    }
    lateinit var name:String
    lateinit var email:String
    lateinit var pass:String
    lateinit var confpass:String
    lateinit var pincode:String
    lateinit var address:String
    lateinit var vehicleno:String
    lateinit var phoneno:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        supportActionBar?.title="Krish-E Driver Register"
        register.setOnClickListener {
            createAccount()
        }
        uploadproof.setOnClickListener {
            val ind=Intent(Intent.ACTION_PICK)
            ind.type="image/+"
            startActivityForResult(ind,0)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==0 && resultCode== Activity.RESULT_OK && data!=null)
        {
            photodata= data.data
            val bitmapcon= MediaStore.Images.Media.getBitmap(contentResolver, photodata)
            val drawablecon=BitmapDrawable(bitmapcon)
            uploadproof.setBackgroundDrawable(drawablecon)
        }
    }
    private fun createAccount()
    {
       name=reg_name.text.toString()
        email=reg_email.text.toString()
        pass=reg_pass.text.toString()
        confpass=reg_confpass.text.toString()
        pincode=reg_pincode.text.toString()
        address=reg_address.text.toString()
        vehicleno=reg_vehicleno.text.toString()
        phoneno=reg_phoneno.text.toString()
        val auth=FirebaseAuth.getInstance()
        if(name.isEmpty()||email.isEmpty()||pass.isEmpty()||confpass.isEmpty()||pincode.isEmpty()||vehicleno.isEmpty()||phoneno.isEmpty())
        {
            return
        }
        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
            if(it.isSuccessful)
            {
                uploadPhotos(it.result?.user?.uid.toString())
            }
            else{
                Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                return@addOnCompleteListener
            }
        }

    }
    private fun uploadPhotos(uid:String)
    {
        val ref=FirebaseStorage.getInstance().getReference("/image/$uid/${UUID.randomUUID()}")
        ref.putFile(photodata!!).addOnSuccessListener {
            Toast.makeText(this,"Photo Added Successfully",Toast.LENGTH_SHORT).show()
            ref.downloadUrl.addOnSuccessListener {
                uploaddatas(it.toString())
            }
        }
    }
    private fun uploaddatas(uris:String)
    {
        val uid=FirebaseAuth.getInstance().currentUser!!.uid
        val databaseref=FirebaseDatabase.getInstance().getReference("/DriverRegister/$uid")
        val setRegister=setValue(name,email,pass,confpass,pincode,address,vehicleno,phoneno
        ,uid,uris)
        databaseref.setValue(setRegister).addOnCompleteListener {
            if(it.isSuccessful)
            {
                Toast.makeText(this,"Data Saved Successfully",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,
                    DriverVehiclePhotos::class.java))
                finish()
            }
            else{
                return@addOnCompleteListener
            }
        }
    }
}
class setValue(var name:String,var email:String,var pass:String,var confpass:String,var pincode:String,var address:String,var vehicleno:String,var phoneno:String
,var uid:String,var photodata:String)
{
    constructor() : this("","","","","","","","","","")
}