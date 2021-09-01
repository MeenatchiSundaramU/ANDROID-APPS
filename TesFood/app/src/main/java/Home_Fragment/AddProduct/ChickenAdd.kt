package Home_Fragment.AddProduct

import HomeActivity.FleshersSplashActivity
import Home_Fragment.AddProduct.RequirementsFrag.RequirementFragment.Companion.get_data
import Home_Fragment.AddProduct.RequirementsFrag.RequirementFragment.Companion.uids
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.location.*
import com.google.firebase.database.FirebaseDatabase

import com.ums.tesfood.R
import kotlinx.android.synthetic.main.activity_chicken_add.*
import kotlinx.android.synthetic.main.activity_chicken_add.addproduct
import kotlinx.android.synthetic.main.fragment_add_product.*
import kotlinx.android.synthetic.main.fragment_add_product.view.*
import kotlinx.android.synthetic.main.fragment_add_product.view.natural
import java.text.SimpleDateFormat
import java.util.*

class ChickenAdd : AppCompatActivity() {
    var fleshname:String?=null
    lateinit var locationRequest: LocationRequest
    val PERMISSION_ID = 1010
    var latitude1:String?=null
    var longitude1:String?=null
    var latitude2:String?=null
    var longitude2:String?=null
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chicken_add)
        fleshname=intent.getStringExtra("CLICK")
        supportActionBar!!.title="Add $fleshname Product"
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        if(fleshname=="Mutton")
        {
            nattukolzhi_new.text="White Goat"
            broiler_new.text="Black Goat"
            broiler_image.setImageResource(R.drawable.goat)
            nattu_image.setImageResource(R.drawable.white_goat)
        }
        else if(fleshname=="Fish")
        {
            chick_mutt_view.visibility=View.INVISIBLE
            fish_view.visibility= View.VISIBLE
        }
        else if(fleshname=="Kaadai")
        {
            chick_mutt_view.visibility=View.INVISIBLE
            kaadai_view.visibility= View.VISIBLE
        }
        else if(fleshname=="VaanKolzhi")
        {
            chick_mutt_view.visibility=View.INVISIBLE
            vaankolzhi_view.visibility= View.VISIBLE
        }
        addproduct.setOnClickListener {
            UploadData()
        }
        add_fish_product.setOnClickListener {
            UploadData()
        }
        add_kaadai_product.setOnClickListener {
            UploadData()
        }
        add_vaankolzhi_product.setOnClickListener {
            UploadData()
        }
    }

    private fun UploadData() {
        var totalflesh:String?=null
        var fleshprice:String?=null
        if(fleshname=="Chicken" || fleshname=="Mutton") {
            totalflesh = total_flesh_new.text.toString()
            fleshprice = flesh_price_new.text.toString()
        }
        else if(fleshname=="Fish")
        {
            totalflesh = total_fish_flesh.text.toString()
            fleshprice = flesh_fish_price.text.toString()
        }
        else if(fleshname=="Kaadai")
        {
            totalflesh = total_kaadai_flesh.text.toString()
            fleshprice = flesh_kaadai_price.text.toString()
        }
        else
        {
            totalflesh = total_vaankolzhi_flesh.text.toString()
            fleshprice = flesh_vaankolzhi_price.text.toString()
        }
        var fleshtype:String?=null
        var fleshimage:String?=null
        if(fleshname=="Chicken") {
            if (nattukolzhi_new.isChecked) {
                fleshtype = "NattuKolzhi"
                fleshimage="https://firebasestorage.googleapis.com/v0/b/tesfood-21d02.appspot.com/o/flesh_photos%2Fbroiler.jpg?alt=media&token=a8a0f779-3e06-4ed8-86cf-a47d927db5e1"
            } else {
                fleshtype = "Broiler"
                fleshimage="https://firebasestorage.googleapis.com/v0/b/tesfood-21d02.appspot.com/o/flesh_photos%2Fnattukolzhi.jpeg?alt=media&token=55345375-bfef-405e-a42f-8ac12558dc01"
            }
        }
        else if(fleshname=="Mutton")
        {
            if (nattukolzhi_new.isChecked) {
                fleshtype = "White Goat"
                fleshimage="https://firebasestorage.googleapis.com/v0/b/tesfood-21d02.appspot.com/o/flesh_photos%2Fwhite_goat.jpg?alt=media&token=6305aac0-02f6-4827-a17f-394a80d2701a"
            } else {
                fleshtype = "Black Goat"
                fleshimage="https://firebasestorage.googleapis.com/v0/b/tesfood-21d02.appspot.com/o/flesh_photos%2Fgoat.png?alt=media&token=87e72dbd-3fdb-448b-b59f-f8e158484759"
            }
        }
        else if(fleshname=="Fish")
        {
            if (fish_1.isChecked) {
                fleshtype = "Fish 1"
                fleshimage="https://firebasestorage.googleapis.com/v0/b/tesfood-21d02.appspot.com/o/flesh_photos%2Ffish_1.jpeg?alt=media&token=2e70149b-5c17-4f15-bb0a-d17cff9e5a05"
            }
            else if (fish_2.isChecked) {
                fleshtype = "Fish 2"
                fleshimage="https://firebasestorage.googleapis.com/v0/b/tesfood-21d02.appspot.com/o/flesh_photos%2Ffish_2.jpeg?alt=media&token=b14392aa-e55f-4782-88c1-ba12934b9e6f"
            }
            else if (fish_4.isChecked) {
                fleshtype = "Fish 4"
                fleshimage="https://firebasestorage.googleapis.com/v0/b/tesfood-21d02.appspot.com/o/flesh_photos%2Ffish_4.jpeg?alt=media&token=349a5765-246c-424c-985d-3e59c4bb5920"
            }
            else if (fish_5.isChecked) {
                fleshtype = "Fish 5"
                fleshimage="https://firebasestorage.googleapis.com/v0/b/tesfood-21d02.appspot.com/o/flesh_photos%2Ffish_5.jpeg?alt=media&token=f32e0d8b-d556-4f0f-bbf7-47f534a93985"
            }
            else {
                fleshtype = "Fish 6"
                fleshimage="https://firebasestorage.googleapis.com/v0/b/tesfood-21d02.appspot.com/o/flesh_photos%2Ffish_6.jpeg?alt=media&token=105c56d3-1b33-4805-b408-fae2eefeb3a1"
            }
        }
        else if(fleshname=="Kaadai")
        {
            fleshtype="Kaadai"
            fleshimage="https://firebasestorage.googleapis.com/v0/b/tesfood-21d02.appspot.com/o/flesh_photos%2Ffish_3.jpeg?alt=media&token=995ae1f1-4c82-4e87-a26e-b348d252dbe0"
        }
        else if(fleshname=="VaanKolzhi")
        {
            fleshtype="VaanKolzhi"
            fleshimage="https://firebasestorage.googleapis.com/v0/b/tesfood-21d02.appspot.com/o/flesh_photos%2FWhatsApp%20Image%202021-04-09%20at%2017.34.37.jpeg?alt=media&token=ad5513a1-8cf9-4168-8183-3a74ef9faab8"
        }

        getLastLocation(totalflesh,fleshprice,fleshtype!!,fleshimage!!)
    }

    @SuppressLint("MissingPermission", "SimpleDateFormat")
    private fun getLastLocation(totalflesh: String, fleshprice: String, fleshtype: String,fleshimage:String) {
        fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
            val location: Location? = task.result
            if (location == null) {
                NewLocationData(this)
            } else {
                latitude1 = location.latitude.toString()
                longitude1 = location.longitude.toString()
              //  val calendar = Calendar.getInstance()
             //   val date = SimpleDateFormat("dd-MM-y").format(calendar.time).toString()
                val upload_ref =
                    FirebaseDatabase.getInstance()
                        .getReference("/FlesherAddProduct/$fleshname/${get_data!!.district}/${get_data!!.city}/${uids}")
                        .push()
                upload_ref.setValue(
                    AddProducts(
                        fleshname!!, fleshtype, totalflesh, fleshprice,
                        get_data!!.phone, latitude1!!, longitude1!!,fleshimage
                    )
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Product Added Successfully", Toast.LENGTH_SHORT)
                            .show()
                        if(fleshname=="Chicken" || fleshname=="Mutton") {
                            total_flesh_new.text.clear()
                            flesh_price_new.text.clear()
                            }
                        else{
                            total_fish_flesh.text.clear()
                            flesh_fish_price.text.clear()
                        }
                        startActivity(Intent(this,ProductAddSplashActivity::class.java))

                    } else {
                        Toast.makeText(this, "Check Your Internet Connection", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

            }
        }
    }
        @SuppressLint("MissingPermission")
        fun NewLocationData(views: Context){
            val locationRequest =  LocationRequest()
            locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            locationRequest.interval = 0
            locationRequest.fastestInterval = 0
            locationRequest.numUpdates = 1
            fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(views)
            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,locationCallback, Looper.myLooper()
            )
        }


        private val locationCallback = object : LocationCallback(){
            @SuppressLint("SetTextI18n")
            override fun onLocationResult(locationResult: LocationResult) {
                val lastLocation: Location = locationResult.lastLocation
                Log.d("Debug:","your last last location: "+ lastLocation.longitude.toString())
                latitude2=lastLocation.latitude.toString()
                longitude2=lastLocation.longitude.toString()
            }
        }

    override fun onPause() {
        super.onPause()
        finish()
    }
}