package com.ums.krishedriver.Mainfragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.ums.krishedriver.AuthenticationActivity.SignInActivity
import com.ums.krishedriver.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title="Notifications"
        FragmentShow(NotificationFragment())
        nav_menu.setOnNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.notifi ->{
                      FragmentShow(NotificationFragment())
                      supportActionBar?.title="Notifications"
                }
                R.id.complete ->{
                    FragmentShow(CompletedFragment())
                    supportActionBar?.title="Share Notifications"
                }
                R.id.profile ->{
                    FragmentShow(ProfileFragment())
                    supportActionBar?.title="Profile"
                }
            }
            return@setOnNavigationItemSelectedListener(true)
        }
    }
    private fun FragmentShow(frag:Fragment)
    {
        supportFragmentManager.beginTransaction().replace(R.id.main_frame,frag)
            .commit()
    }

    override fun onStart() {
        super.onStart()
        if(FirebaseAuth.getInstance().uid==null)
        {
            startActivity(Intent(this,SignInActivity::class.java))
        }
    }
}