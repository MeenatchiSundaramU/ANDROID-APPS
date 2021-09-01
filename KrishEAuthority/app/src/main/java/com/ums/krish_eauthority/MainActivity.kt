package com.ums.krish_eauthority

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
  //  lateinit var toolbars: Toolbar
   // lateinit var frameLayout: FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      drawerLayout=findViewById(R.id.autordrawLayout)
        setupToolbar()
        var actionBarDrawerToggle=ActionBarDrawerToggle(this,drawerLayout,R.string.open_drawer,R.string.close_drawer)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        defaultopen()
        navview.setNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.addnewcrops->{
                            defaultopen()
                            drawerLayout.closeDrawers()
                }
                R.id.updateprice->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame,UpdatePriceFragment())
                        .commit()
                    supportActionBar?.setTitle("Update Crops Price")
                    drawerLayout.closeDrawers()
                }
                R.id.buyershow->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame,BuyershowFragment())
                        .commit()
                    supportActionBar?.setTitle("Buyer Show")
                    drawerLayout.closeDrawers()
                }
                R.id.settleshow->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame,FarmerssettlementFragment())
                        .commit()
                    supportActionBar?.setTitle("Farmers Settlement")
                    drawerLayout.closeDrawers()
                }
                R.id.paymenthist->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame,PaymentHistory())
                        .commit()
                    supportActionBar?.setTitle("Payment History")
                    drawerLayout.closeDrawers()
                }

            }
            return@setNavigationItemSelectedListener(true)
        }
    }
    fun setupToolbar()
    {
        setSupportActionBar(authority_toolbar)
        supportActionBar?.title="Krish-E Authority App"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id=item.itemId
        if(id==android.R.id.home)
        {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }
    private fun defaultopen()
    {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame,AddNewFragment())
            .commit()
        supportActionBar?.setTitle("Add New Crops")
        navview.setCheckedItem(R.id.addnewcrops)
    }

    }
