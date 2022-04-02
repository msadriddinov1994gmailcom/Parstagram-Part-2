package com.example.parstagrampart2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.parstagrampart2.fragments.SignInFragment
import com.example.parstagrampart2.fragments.SignUpFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.parse.ParseUser


class LoginActivity : AppCompatActivity() {

    private val signInFragment: Fragment = SignInFragment()
    private val signUpFragment: Fragment = SignUpFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val fragmentManager: FragmentManager = supportFragmentManager
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.navigation_menu_for_login)
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.action_signUp -> fragment = signUpFragment
                R.id.action_signIn -> fragment = signInFragment
            }
            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
            true
        }
        bottomNavigationView.selectedItemId = R.id.action_signIn

    }


    companion object {
        const val TAG = "LoginActivity"
    }


}