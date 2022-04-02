package com.example.parstagrampart2.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.parstagrampart2.LoginActivity
import com.example.parstagrampart2.MainActivity
import com.example.parstagrampart2.R
import com.parse.ParseInstallation
import com.parse.ParseUser

class SignInFragment : Fragment() {

    lateinit var btn_signIn : Button
    lateinit var et_username_for_signIn: EditText
    lateinit var et_password_for_signIn: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_signin, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        et_username_for_signIn = view.findViewById(R.id.et_username_for_signIn)
        et_password_for_signIn = view.findViewById(R.id.et_password_for_signIn)
        btn_signIn = view.findViewById(R.id.btn_signIn)
        btn_signIn.setOnClickListener {
            signIn(et_username_for_signIn.text.toString(), et_password_for_signIn.text.toString())
        }

    }

    private fun signIn(username: String, password: String) {
        ParseUser.logInInBackground(username, password, ({ user, e ->
            if (user != null) {
                Toast.makeText(view?.context, "Successfully signed in.", Toast.LENGTH_SHORT).show()
                goToMainActivity()
            } else {
                Toast.makeText(view?.context, "No $password", Toast.LENGTH_SHORT).show()
                Log.e("LoginActivity", "Failed")
            }})
        )
    }

    private fun goToMainActivity(){
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)

    }


}