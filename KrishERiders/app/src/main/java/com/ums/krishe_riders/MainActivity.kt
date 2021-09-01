package com.ums.krishe_riders

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ums.krishe_riders.NavigationFrag.NotificationsFragment
import com.ums.krishe_riders.NavigationFrag.SettingsFragment
import com.ums.krishe_riders.NavigationFrag.VehicleShowFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.title="Vehicle Show"
        fragmentSelection(VehicleShowFragment())
        nav_view.setOnNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.vehicle_show->{
                         supportActionBar?.title="Vehicle Show"
                         fragmentSelection(VehicleShowFragment())
                }
                R.id.notifi->{
                    supportActionBar?.title="Notifications"
                    fragmentSelection(NotificationsFragment())
                }
                R.id.settings->{
                    supportActionBar?.title="Settings"
                    fragmentSelection(SettingsFragment())
                }
            }
            return@setOnNavigationItemSelectedListener(true)
        }
    }
    private fun fragmentSelection(frag:Fragment)
    {
        supportFragmentManager.beginTransaction().replace(R.id.main_frame,frag)
            .commit()
    }
}