package Home_Fragment.AddProduct

import HomeActivity.HomeActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.ums.tesfood.R

class ProductAddSplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_add_splash)
        Handler().postDelayed({
            startActivity(Intent(this,HomeActivity::class.java))
        },2000)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}