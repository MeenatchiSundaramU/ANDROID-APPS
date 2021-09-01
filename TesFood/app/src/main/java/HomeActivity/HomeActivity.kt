package HomeActivity

import Home_Fragment.*
import Home_Fragment.AddProduct.FleshImageFragment
import Home_Fragment.AddProduct.RequirementsFrag.RequirementFragment
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ums.tesfood.Authentication.sign_up_class
import com.ums.tesfood.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        defaultOpen()
        home_view.setOnNavigationItemSelectedListener {
       when(it.itemId)
           {
             R.id.requirement->{
                      defaultOpen()
             }
           R.id.add_pro->{
               supportActionBar!!.title="Add Product"
               supportFragmentManager.beginTransaction()
                   .replace(R.id.home_frame,
                       FleshImageFragment()
                   )
                   .commit()
           }
           R.id.update->{
               supportActionBar!!.title="Update"
               supportFragmentManager.beginTransaction()
                   .replace(R.id.home_frame, UpdateFragment())
                   .commit()
           }
           R.id.sold_out->{
               supportActionBar!!.title="Sold Out"
               supportFragmentManager.beginTransaction()
                   .replace(R.id.home_frame,SoldOutFragment())
                   .commit()
           }
//           R.id.profile->{
//               supportActionBar!!.title="Profile"
//               supportFragmentManager.beginTransaction()
//                   .replace(R.id.home_frame, ProfileFragment())
//                   .commit()
//           }
           R.id.admin_contact->{
               supportActionBar!!.title="Admin Contact"
               supportFragmentManager.beginTransaction()
                   .replace(R.id.home_frame, AdminFragment())
                   .commit()
           }
           }
            return@setOnNavigationItemSelectedListener(true)
        }
    }

    private fun defaultOpen() {
        supportActionBar!!.title="Requirements"
        supportFragmentManager.beginTransaction()
            .replace(R.id.home_frame, RequirementFragment()
            )
            .commit()
    }
    fun isLocationEnabled():Boolean{
        //this function will return to us the state of the location service
        //if the gps or the network provider is enabled then it will return true otherwise it will return false

        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER)
    }
    override fun onStart() {
        super.onStart()
        if(!isLocationEnabled()) {
            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        }
    }
}
