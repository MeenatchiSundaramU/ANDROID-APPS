package com.ums.teslasautomation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFragment(RelayFragment())
        bottom_menu.setOnNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.relay->{
                    openFragment(RelayFragment())
                    main_title.text="Relay"
                }
                R.id.temp_sensor->{
                    openFragment(TemperatureFragment())
                    main_title.text="Temperature Sensor"
                }
                R.id.power->{
                    openFragment(PowerFragment())
                    main_title.text="Power"
                }
                R.id.graph->{
                    openFragment(Graph_Main_Fragment())
                    main_title.text="Graph"
                }
                R.id.data_log->{
                    openFragment(DataLogFragment())
                    main_title.text="Data Log"
                }
            }
            return@setOnNavigationItemSelectedListener(true)
        }
    }
    fun openFragment(frag:Fragment)
    {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame,frag)
            .commit()
    }
}