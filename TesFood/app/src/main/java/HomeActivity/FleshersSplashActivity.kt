package HomeActivity

import Home_Fragment.AddProduct.RequirementsFrag.RequirementFragment
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ums.tesfood.Authentication.sign_up_class
import com.ums.tesfood.R

class FleshersSplashActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fleshers_splash)
        supportActionBar!!.hide()
        if (CheckPermission(this)) {
            Handler().postDelayed(
                {
                    startActivity(Intent(this, HomeActivity::class.java))
                }, 5000
            )
        } else {
            RequestPermission()
        }
    }
    fun CheckPermission(views: Context):Boolean{
        //this function will return a boolean
        //true: if we have permission
        //false if not
        if(
            ActivityCompat.checkSelfPermission(views,android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(views,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        )
        {
            return true
        }
        return false

    }
    fun RequestPermission(){
        //this function will allows us to tell the user to requesut the necessary permsiion if they are not garented
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,android.Manifest.permission.ACCESS_FINE_LOCATION),
            100
        )
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == 100){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.d("Debug:","You have the Permission")
                Handler().postDelayed(
                    {
                        startActivity(Intent(this, HomeActivity::class.java))
                    }, 3000
                )
            }
        }
    }
    //Getting the data for the flesher to display profile
    override fun onStart() {
        super.onStart()

    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}