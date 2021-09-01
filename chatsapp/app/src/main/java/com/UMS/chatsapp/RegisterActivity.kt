package com.UMS.chatsapp

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.Intent.ACTION_PICK
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_register.*
import java.io.ByteArrayOutputStream
import java.util.*

class RegisterActivity : AppCompatActivity() {
     var datas:Uri?=null
    var cameraimage:Bitmap?=null
    var email:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.title="NEW USER REGISTER"
        regbtn.setOnClickListener {
            performregister()
        }
        regphoto.setOnClickListener {
            val build=AlertDialog.Builder(this)
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
        alreadyac.setOnClickListener {
            val ind=Intent(this,LoginActivity::class.java)
            ind.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(ind)
        }
    }
    private fun performregister()
    {

        if(datas==null && cameraimage==null)
        {
            Toast.makeText(this,"Choose Photos",Toast.LENGTH_SHORT).show()
            return
        }
        email=regemail.text.toString()
        val pass=regpass.text.toString()
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()||email!!.isEmpty())
        {
            regemail.requestFocus()
            regemail.error="Enter Valid Email"
            return
        }
        if(regusername.text.toString().isEmpty())
        {
            regusername.requestFocus()
            regusername.error="Enter Valid Name"
            return
        }
        if(regpass.text.toString().isEmpty()||regconfpass.text.toString().isEmpty()||regpass.text.toString().length<=7||regpass.text.toString().compareTo(regconfpass.text.toString())!=0)
        {
            regpass.requestFocus()
            regpass.error="Enter Valid Password"
            regconfpass.requestFocus()
            regconfpass.error="Enter Valid Password"
            return
        }
        regprogress.visibility= View.VISIBLE
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email!!,pass).addOnCompleteListener {
            if(!it.isSuccessful)
            {

                Toast.makeText(this@RegisterActivity,"Check Internet connection",Toast.LENGTH_SHORT).show()
                return@addOnCompleteListener
            }
            else{
               // Toast.makeText(this@RegisterActivity,it.result?.user?.uid,Toast.LENGTH_SHORT).show()
                uploadimage(it.result?.user?.uid.toString())

            }
        }
            .addOnFailureListener {
                regprogress.visibility= View.GONE
                Toast.makeText(this@RegisterActivity,it.message,Toast.LENGTH_SHORT).show()
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

                Toast.makeText(this@RegisterActivity, data.data.toString(), Toast.LENGTH_SHORT)
                    .show()

            }
            else if(requestCode==100 && data!=null)
            {
                datas=data.data
                cameraimage= data.extras?.get("data") as Bitmap

                circularimage.setImageBitmap(cameraimage)
                regphoto.alpha=0f

                Toast.makeText(this@RegisterActivity, data.data.toString(), Toast.LENGTH_SHORT)
                    .show()
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
                val baos=ByteArrayOutputStream()
                val filename = userid
                val ref = FirebaseStorage.getInstance().getReference("/images/$filename")
                cameraimage!!.compress(Bitmap.CompressFormat.JPEG,100,baos)
                val imgbytes=baos.toByteArray()
                ref.putBytes(imgbytes).addOnSuccessListener {
                    Toast.makeText(this,"Camera Photo Saved Suucessfully",Toast.LENGTH_SHORT).show()
                    ref.downloadUrl.addOnSuccessListener {
                        Log.d("checkings", "The photo added successfully $it")
                        uploaddata(it.toString())
                    }
                }

            }

        }
        else{
            val filename = userid
            val ref = FirebaseStorage.getInstance().getReference("/images/$filename")
            ref.putFile(datas!!).addOnSuccessListener {
                Log.d("checkings", "The photo added successfully${it.metadata?.path}")
                ref.downloadUrl.addOnSuccessListener {
                    it.toString()
                    Log.d("checkings", "The photo added successfully $it")
                    uploaddata(it.toString())
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
    private fun uploaddata(photourl:String)
    {
        val uid=FirebaseAuth.getInstance().uid.toString()
        val names=regusername.text.toString()
        val dataref=FirebaseDatabase.getInstance().getReference("/register/$uid")
        val dataref2=FirebaseDatabase.getInstance().getReference("/forget/$names")
        val dataset=Register(uid,regusername.text.toString(),regemail.text.toString(),regpass.text.toString(),photourl)
        if(email==null)return
        val dataset2=forget(uid,email!!)
        dataref2.setValue(dataset2).addOnSuccessListener {
            regprogress.visibility= View.GONE
            Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show()
        }
        dataref.setValue(dataset).addOnSuccessListener {
            regprogress.visibility= View.GONE
            Toast.makeText(this,"Datas Saved Successfully",Toast.LENGTH_SHORT).show()
            val ind=Intent(this,HomeActivity::class.java).apply {
                flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(ind)
        }
    }

}
class forget(val uids:String,val emails:String){
    constructor() : this("","")
}