package com.ums.krishedriver.AuthenticationActivity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.ums.krishedriver.Mainfragment.MainActivity
import com.ums.krishedriver.R
import kotlinx.android.synthetic.main.activity_driver_vehicle_photos.*
import java.util.*

class DriverVehiclePhotos : AppCompatActivity() {
    companion object{
        var data1: Uri?=null
        var data2: Uri?=null
        var data3: Uri?=null
        var getdata1: String?=null
        var getdata2: String?=null
        var getdata3: String?=null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_vehicle_photos)
        supportActionBar?.title="Vehicle Photos Upload"
       regphoto1.setOnClickListener {
           openphotos(1)
       }
        reg_photo2.setOnClickListener {
            openphotos(2)
        }
        regphoto3.setOnClickListener {
            openphotos(3)
        }
        photo_submits.setOnClickListener {
            uploadPhotos()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== Activity.RESULT_OK && data!=null)
        {
            when(requestCode)
            {
               1->{
                   data1 =data.data
                   val getbit=MediaStore.Images.Media.getBitmap(contentResolver,
                       data1
                   )
                   circularimage1.setImageBitmap(getbit)
                   regphoto1.alpha=0f
               }
                2->{
                    data2 =data.data
                    val getbit=MediaStore.Images.Media.getBitmap(contentResolver,
                        data2
                    )
                    circularimage2.setImageBitmap(getbit)
                    reg_photo2.alpha=0f
                }
                3->{
                    data3 =data.data
                    val getbit=MediaStore.Images.Media.getBitmap(contentResolver,
                        data3
                    )
                    circularimage3.setImageBitmap(getbit)
                    regphoto3.alpha=0f
                }
            }
        }
    }
    private fun openphotos(checks:Int)
    {
        val ind=Intent(Intent.ACTION_PICK)
        ind.type="image/+"
        startActivityForResult(ind,checks)
    }
    private fun uploadPhotos()
    {
        val auth=FirebaseAuth.getInstance().currentUser!!.uid
        val ref1=FirebaseStorage.getInstance().getReference("/image/$auth/${UUID.randomUUID()}")
        val ref2=FirebaseStorage.getInstance().getReference("/image/$auth/${UUID.randomUUID()}")
        val ref3=FirebaseStorage.getInstance().getReference("/image/$auth/${UUID.randomUUID()}")
        ref1.putFile(data1!!).addOnSuccessListener {
            Toast.makeText(this,"First",Toast.LENGTH_SHORT).show()
            ref1.downloadUrl.addOnSuccessListener {
                getdata1 =it.toString()
            }
        }
        ref2.putFile(data2!!).addOnSuccessListener {
            Toast.makeText(this,"Second",Toast.LENGTH_SHORT).show()
            ref2.downloadUrl.addOnSuccessListener {
                getdata2 =it.toString()
            }
        }
        ref3.putFile(data3!!).addOnSuccessListener {
            Toast.makeText(this,"Third",Toast.LENGTH_SHORT).show()
            ref3.downloadUrl.addOnSuccessListener {
                getdata3 =it.toString()
                uploaddatas()
            }
        }
    }
    private fun uploaddatas()
    {
        val uid=FirebaseAuth.getInstance().currentUser!!.uid
        val ref=FirebaseDatabase.getInstance().getReference("/VehiclePhotos/$uid").push()
        val s= Photoss(
            getdata1!!,
            getdata2!!,
            getdata3!!
        )
        ref.setValue(s).addOnSuccessListener {
            Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,
                MainActivity::class.java))
            finish()
        }
    }

}
class Photoss(var photo1:String,var photo2:String,var photo3:String)
{
    constructor() : this("","","")
}