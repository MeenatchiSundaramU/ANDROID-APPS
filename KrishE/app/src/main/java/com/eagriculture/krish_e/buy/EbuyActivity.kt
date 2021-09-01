package com.eagriculture.krish_e.buy

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import com.eagriculture.krish_e.R
import com.google.android.material.navigation.NavigationView
import java.security.AccessController.getContext

class EbuyActivity : AppCompatActivity() {
    lateinit var buydrawer:DrawerLayout
    lateinit var buycoord:CoordinatorLayout
    lateinit var toolbar: Toolbar
    lateinit var buyframe:FrameLayout
    lateinit var buynavview:NavigationView
     var buymenuitem:MenuItem?=null
    lateinit var drawername: TextView
    lateinit var drawermobile: TextView
    lateinit var profshared: SharedPreferences
    lateinit var view: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ebuy)
      //  var buyget=intent.getStringExtra("payget")
        profshared=getSharedPreferences(getString(R.string.reg_pass),
            Context.MODE_PRIVATE
        )
        var name=profshared.getString("name","User")
        var mobile=profshared.getString("mobilenumber","04637221724")
        buydrawer=findViewById(R.id.buyDrawerlayout)
        buycoord=findViewById(R.id.buycoord)
        toolbar=findViewById(R.id.buytool)
        buyframe=findViewById(R.id.buyframe)
        buynavview=findViewById(R.id.buynavview)
        setBuyToolbar()

        var buyactionBarDrawerToggle=ActionBarDrawerToggle(this,buydrawer,R.string.open_drawer,R.string.close_drawer)
        buydrawer.addDrawerListener(buyactionBarDrawerToggle)
        buyactionBarDrawerToggle.syncState()


        defaultbuyopen()
        view=buynavview.getHeaderView(0)
        drawername=view.findViewById(R.id.buydrawer_name)
        drawermobile=view.findViewById(R.id.buydrawer_mobileno)
        drawername.setText(name)
        drawermobile.setText(mobile)

        buynavview.setNavigationItemSelectedListener {
            if(buymenuitem!=null)
            {
                buymenuitem?.isChecked=false
                buymenuitem?.isCheckable=false
            }
            it.isChecked=true
            it.isCheckable=true
            buymenuitem=it

            when(it.itemId)
            {
                R.id.buymarketprice->{

                    defaultbuyopen()
                    buydrawer.closeDrawers()
                }
                R.id.buyproducts->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.buyframe,BuyproductsFragment())
                        .commit()
                    buydrawer.closeDrawers()
                    supportActionBar?.title="Buy Product"
                }
                R.id.buycart->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.buyframe,BuycartFragment())
                        .commit()
                    buydrawer.closeDrawers()
                    supportActionBar?.title="Cart"
                }
                R.id.buytrackproduct->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.buyframe,TrackorderFragment())
                        .commit()
                    buydrawer.closeDrawers()
                    supportActionBar?.title="Track Order"
                }
                R.id.buypaymentinfo->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.buyframe,BuypaymentFragment())
                        .commit()
                    buydrawer.closeDrawers()
                    supportActionBar?.title="Payment Info"
                }
                R.id.buyfeedback->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.buyframe,BuyfeedbackFragment())
                        .commit()
                    buydrawer.closeDrawers()
                    supportActionBar?.title="Feedback"
                }
                R.id.buyprofile->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.buyframe,BuyprofileFragment())
                        .commit()
                    buydrawer.closeDrawers()
                    supportActionBar?.title="Profile"
                }
                R.id.buyhelp->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.buyframe,BuyhelpFragment())
                        .commit()
                    buydrawer.closeDrawers()
                    supportActionBar?.title="Help & Support"
                }
                R.id.buyabout->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.buyframe,AboutebuyFragment())
                        .commit()
                    buydrawer.closeDrawers()
                    supportActionBar?.title="About E-buy"
                }

            }
            return@setNavigationItemSelectedListener (true)
        }

    }
    fun setBuyToolbar()
    {
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var buymenuid=item.itemId
        if(buymenuid==android.R.id.home)
        {
            buydrawer.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }
    fun defaultbuyopen()
    {
        var frag=supportFragmentManager.beginTransaction()
        var init=BuymarketpriceFragment()
        buynavview.setCheckedItem(R.id.buymarketprice)
        frag.replace(R.id.buyframe,init).commit()
        supportActionBar?.title="Market Price"
    }

    override fun onBackPressed() {
        var fragid=supportFragmentManager.findFragmentById(R.id.buyframe)
        when(fragid)
        {
            !is BuymarketpriceFragment->defaultbuyopen()
            else->
            {
                super.onBackPressed()
            }
        }

    }
}