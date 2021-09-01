package com.eagriculture.krish_e.sell

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.eagriculture.krish_e.*
import com.google.android.material.navigation.NavigationView

class EsellActivity : AppCompatActivity() {
    lateinit var eselldrawerlayout:DrawerLayout
    lateinit var esellcoordinate: CoordinatorLayout
    lateinit var eselltoolbar:androidx.appcompat.widget.Toolbar
    lateinit var esellframe:FrameLayout
    lateinit var esellnavview: NavigationView
     lateinit var drawername:TextView
      lateinit var drawermobile:TextView
    lateinit var view:View

    lateinit var esell:SharedPreferences
    var esellprevitems:MenuItem?=null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_esell)
        esell=getSharedPreferences(getString(R.string.reg_pass), Context.MODE_PRIVATE)

       var name=esell.getString("name","User")
       var mobile=esell.getString("mobilenumber","04637221724")

        eselldrawerlayout=findViewById(R.id.selldrawerlayout)
        esellcoordinate=findViewById(R.id.sellcoordinatelayout)
        eselltoolbar=findViewById(R.id.selltoolbar)
        esellframe=findViewById(R.id.sellframe)
        esellnavview=findViewById(R.id.sellnavview)
        setToolbar()
        var eselldrawertoggle=ActionBarDrawerToggle(this,eselldrawerlayout,
            R.string.open_drawer,
            R.string.close_drawer
        )
        eselldrawerlayout.addDrawerListener(eselldrawertoggle)
        eselldrawertoggle.syncState()
        esellMarketFragment()
        view=esellnavview.getHeaderView(0)
        drawername=view.findViewById(R.id.drawer_name)
        drawermobile=view.findViewById(R.id.drawer_mobileno)
        drawername.setText(name)
        drawermobile.setText(mobile)
        esellnavview.setNavigationItemSelectedListener {
            if(esellprevitems!=null)
            {
                esellprevitems?.isChecked=false
                esellprevitems?.isCheckable=false
            }
            it.isChecked=true
            it.isCheckable=true
            esellprevitems=it
               when(it.itemId)
               {

                   R.id.marketprice ->{

                           esellMarketFragment()
                           eselldrawerlayout.closeDrawers()

                   }
                  R.id.addproducts ->{
                       supportFragmentManager.beginTransaction()
                           .replace(
                               R.id.sellframe,
                               EselladdproductsFragment()
                           )
                           .commit()
                       eselldrawerlayout.closeDrawers()
                      supportActionBar?.title="Add Products"

                   }
                   R.id.selledproducts ->{
                       supportFragmentManager.beginTransaction()
                           .replace(
                               R.id.sellframe,
                               EsellselledproductsFragment()
                           )
                           .commit()
                       eselldrawerlayout.closeDrawers()
                       supportActionBar?.title="Sold Products"

                   }
                   R.id.cancelproducts ->{
                       supportFragmentManager.beginTransaction()
                           .replace(
                               R.id.sellframe,
                               EsellcancelproductsFragment()
                           )
                           .commit()
                       eselldrawerlayout.closeDrawers()
                       supportActionBar?.title="Cancel Products"

                   }
                   R.id.statusofproducts ->{
                       supportFragmentManager.beginTransaction()
                           .replace(
                               R.id.sellframe,
                               EsellstatusFragment()
                           )
                           .commit()
                       eselldrawerlayout.closeDrawers()
                       supportActionBar?.title="Status Of Products"

                   }
                   R.id.notifications ->{
                       supportFragmentManager.beginTransaction()
                           .replace(
                               R.id.sellframe,
                               EsellnotifiFragment()
                           )
                           .commit()
                       eselldrawerlayout.closeDrawers()
                       supportActionBar?.title="Notifications"

                   }
                   R.id.feedback ->{
                       supportFragmentManager.beginTransaction()
                           .replace(
                               R.id.sellframe,
                               EsellfeedbackFragment()
                           )
                           .commit()
                       eselldrawerlayout.closeDrawers()
                       supportActionBar?.title="Feedback"

                   }
                   R.id.profile ->{
                       supportFragmentManager.beginTransaction()
                           .replace(
                               R.id.sellframe,
                               EsellprofileFragment()
                           )
                           .commit()
                       eselldrawerlayout.closeDrawers()
                       supportActionBar?.title="Profile"

                   }
                   R.id.help ->{
                       supportFragmentManager.beginTransaction()
                           .replace(
                               R.id.sellframe,
                               EsellhelpFragment()
                           )
                           .commit()
                       eselldrawerlayout.closeDrawers()
                       supportActionBar?.title="Help & Support"

                   }
                   R.id.aboutesell ->{
                       supportFragmentManager.beginTransaction()
                           .replace(
                               R.id.sellframe,
                               EsellaboutFragment()
                           )
                           .commit()
                       eselldrawerlayout.closeDrawers()
                       supportActionBar?.title="About E-Sell"

                   }
               }
            return@setNavigationItemSelectedListener true
        }
    }
    fun setToolbar()
    {
        setSupportActionBar(eselltoolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var esellnavselectid=item.itemId
        if(esellnavselectid==android.R.id.home)
        {
            eselldrawerlayout.openDrawer(GravityCompat.START)
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        var esellframeid=supportFragmentManager.findFragmentById(R.id.sellframe)
        when(esellframeid)
        {
            !is EsellmarketpriceFragment -> esellMarketFragment()
            else->{
                super.onBackPressed()
            }
        }

    }
    fun esellMarketFragment()
    {
        val frag=supportFragmentManager.beginTransaction()
        val rep= EsellmarketpriceFragment()
        frag.replace(R.id.sellframe,rep).commit()
        supportActionBar?.title="Crops Suggestion Price"
        esellnavview.setCheckedItem(R.id.marketprice)
    }
}