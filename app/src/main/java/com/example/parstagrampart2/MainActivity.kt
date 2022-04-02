package com.example.parstagrampart2

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parstagrampart2.fragments.HomeFragment
import com.example.parstagrampart2.fragments.PostFragment
import com.example.parstagrampart2.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import java.io.File

class MainActivity : AppCompatActivity() {

    private val homeFragment: Fragment = HomeFragment()
    private val postFragment: Fragment = PostFragment()
    private val profileFragment: Fragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation_for_MainActivity)
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.action_home -> fragment = homeFragment
                R.id.action_post -> fragment = postFragment
                R.id.action_profile -> fragment = profileFragment
            }
            fragmentManager.beginTransaction().replace(R.id.fragment_container_for_MainActivity, fragment).commit()
            true
        }
        bottomNavigationView.selectedItemId = R.id.action_signIn

    }

}