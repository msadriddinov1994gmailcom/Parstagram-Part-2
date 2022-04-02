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
import android.widget.Toast
import com.example.parstagrampart2.LoginActivity
import com.example.parstagrampart2.MainActivity
import com.example.parstagrampart2.R
import com.parse.ParseUser


class SignUpFragment : Fragment() {

    lateinit var firstName : EditText
    lateinit var lastName: EditText
    lateinit var email: EditText
    lateinit var et_username_for_signUp: EditText
    lateinit var et_password_for_signUp: EditText
    lateinit var btn_signUp : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_signup, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        et_username_for_signUp = view.findViewById(R.id.et_username_for_signUp)
        et_password_for_signUp = view.findViewById(R.id.et_password_for_signUp)
        btn_signUp = view.findViewById(R.id.btn_signUp)
        btn_signUp.setOnClickListener {
            signUp(et_username_for_signUp.text.toString(), et_password_for_signUp.text.toString())
        }

    }

    private fun signUp(username: String, password: String) {
        val user = ParseUser()
        user.setUsername(username)
        user.setPassword(password)

        user.signUpInBackground { e ->
            if (e == null) {
                Toast.makeText(context, "Successfully signed up.", Toast.LENGTH_SHORT).show()
                goToMainActivity()
            } else {
                Toast.makeText(context, "Failed to sign up.", Toast.LENGTH_SHORT).show()
                Log.e(TAG, "Failed to sign up.")
            }
        }
    }

    private fun goToMainActivity(){
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
    }
    companion object {
        const val TAG = "LoginActivity"
    }


}