package com.ums.krish_eauthority

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.Intent.ACTION_PICK
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.shape.MarkerEdgeTreatment
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_add_new.*
import java.util.*
import kotlin.random.Random
import android.content.ContentResolver as contentResolver


class AddNewFragment : Fragment() {
 var datas: Uri?=null
    lateinit var progressBarss:ProgressBar
    var uploadphotos:String?=null
   // var CropName:String?=null
   // var BasePrice:String?=null
    //var MarketPrice:String?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_add_new, container, false)
        progressBarss=view.findViewById(R.id.progressbar)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addimage.setOnClickListener {
            val ind=Intent(Intent(ACTION_PICK))
            ind.type="image/+"
            startActivityForResult(ind,0)
        }
        addbtn.setOnClickListener {
            uploaddata()
        }


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==0 && resultCode==RESULT_OK && data!=null) {
            datas = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver, datas)
            circularimage.setImageBitmap(bitmap)
            addimage.alpha = 0f
            Toast.makeText(context, datas.toString(), Toast.LENGTH_SHORT).show()
            datas?.let{
                uploadphoto(it)


            }
        }

    }
    fun uploadphoto(photo:Uri)
    {
        progressBarss.visibility=View.VISIBLE
        val id= UUID.randomUUID().toString()
        val ref=FirebaseStorage.getInstance().getReference("/cropphotos/$id")
        ref.putFile(photo).addOnSuccessListener { Toast.makeText(context,"Photo Added Succesfull",
            Toast.LENGTH_SHORT).show()
        ref.downloadUrl.addOnSuccessListener {
            progressBarss.visibility=View.GONE
            uploadphotos=it.toString()
        //    Toast.makeText(context,it.toString(),Toast.LENGTH_SHORT).show()

        }}
            .addOnFailureListener{
                Toast.makeText(context,it.message.toString(),Toast.LENGTH_SHORT).show()
            }
        }
    private fun uploaddata()
    {progressBarss.visibility=View.VISIBLE
       val CropName=addcropsname.text.toString()
      val  BasePrice=addbase.text.toString()
      val  MarketPrice=addmarket.text.toString()
        val ref=FirebaseDatabase.getInstance().getReference("/addcrops/$CropName")
        val ref2=FirebaseDatabase.getInstance().getReference("/BuyerShow/$CropName")
        val s= uploadphotos?.let { updata(CropName,BasePrice,MarketPrice, it) }
        ref.setValue(s).addOnSuccessListener {
            Toast.makeText(context,"Datas Saved Successfully",Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener {
                Toast.makeText(context,it.message.toString(),Toast.LENGTH_SHORT).show()
            }
        val s1=buyershowszero(CropName,"0","0","0","0","0","0",uploadphotos!!)
        ref2.setValue(s1).addOnSuccessListener {
            progressBarss.visibility=View.GONE
            Toast.makeText(context,"Datas Show Saved Successfully",Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener {
                Toast.makeText(context,it.message.toString(),Toast.LENGTH_SHORT).show()
            }
        }
    }
class buyershowszero(val cropname:String,val highqualityQuantity:String,val mediumqualityQuantity:String,val lowqualityQuantity:String,val highprice:String,
                     val medprice:String,val lowprice:String,val photouri:String)
{
    constructor() : this("","","","","","","","")
}
