package com.codepath.flashy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.parse.ParseUser

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener {
                item ->

            var fragmentToShow: Fragment? = null
            when (item.itemId){

                R.id.action_Logout ->{
                    ParseUser.logOut()
                    val currentUser = ParseUser.getCurrentUser() // this will now be null
                    val intent = Intent(this ,LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }


            }
            if (fragmentToShow != null){

            }
            true
        }


    }
}