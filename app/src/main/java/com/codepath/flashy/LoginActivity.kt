package com.codepath.flashy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.parse.Parse
import com.parse.ParseUser


class LoginActivity  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build());


        //Check if User is already logged in
        // If there is, go to main
        if (ParseUser.getCurrentUser() != null) {
            Toast.makeText(this,"Logged In", Toast.LENGTH_SHORT).show()
            goToMainActivity()
        }
        val login = findViewById<TextView>(R.id.login)
        val signup = findViewById<TextView>(R.id.signup)
        val loginLayout=findViewById<LinearLayout>(R.id.loginLayout)
        val signupLayout=findViewById<LinearLayout>(R.id.signupLayout)
        val tvHeader= findViewById<TextView>(R.id.tvHeader)
        login.setOnClickListener() {
            login.background = resources.getDrawable(R.drawable.switch_tracks, null)
            login.setTextColor(resources.getColor(R.color.white))
            signup.background=null
            signup.setTextColor(resources.getColor(R.color.black))
            signupLayout.visibility = View.GONE
            loginLayout.visibility = View.VISIBLE
            tvHeader.text= "Welcome back!"

        }
        signup.setOnClickListener() {
            signup.background = resources.getDrawable(R.drawable.switch_tracks, null)
            signup.setTextColor(resources.getColor(R.color.white))
            login.background=null
            login.setTextColor(resources.getColor(R.color.black))
            loginLayout.visibility = View.GONE
            signupLayout.visibility = View.VISIBLE
            tvHeader.text= "Create your Flashy account"

        }
        findViewById<Button>(R.id.bLogin).setOnClickListener {
            val username = findViewById<TextInputLayout>(R.id.etUserName).editText?.text.toString()
            val password = findViewById<TextInputLayout>(R.id.etPassword).editText?.text.toString()
            loginUser(username, password)

        }



        findViewById<Button>(R.id.bSignup).setOnClickListener {
            val usernameup = findViewById<TextInputLayout>(R.id.etUserNameup).editText?.text.toString()
            val passwordup = findViewById<TextInputLayout>(R.id.etPasswordup).editText?.text.toString()
            val confirmPassword = findViewById<TextInputLayout>(R.id.etPasswordConfirm).editText?.text.toString()
            if (passwordup==confirmPassword) {
                signUpUser(usernameup, passwordup)
                findViewById<TextInputLayout>(R.id.etUserNameup).editText?.text?.clear()
                findViewById<TextInputLayout>(R.id.etPasswordup).editText?.text?.clear()
                findViewById<TextInputLayout>(R.id.etPasswordConfirm).editText?.text?.clear()

            }
            else
                Toast.makeText(this,"Password and confirm password don't match", Toast.LENGTH_SHORT).show()


        }
    }

    private fun signUpUser(username: String, password: String){
        // Create the ParseUser
        val user = ParseUser()

        // Set fields for the user to be created
        user.setUsername(username)
        user.setPassword(password)


        user.signUpInBackground { e ->
            if (e == null) {
                // Hooray! Logged in!
                Toast.makeText(this,"Account Created", Toast.LENGTH_SHORT).show()
            } else {
                e.printStackTrace()
                Toast.makeText(this,"Could not create Account", Toast.LENGTH_SHORT).show()

                // Sign up didn't succeed. Look at the ParseException
                // to figure out what went wrong
            }
        }
    }

    private fun loginUser(username:String, password: String) {
        ParseUser.logInInBackground(username, password, ({ user, e ->
            if (user != null) {
                Log.i(TAG, "Logged in!")
                goToMainActivity()
            } else {
                e.printStackTrace()
                Toast.makeText(this,"Could not log in", Toast.LENGTH_SHORT).show()
            }})
        )

    }

    private fun goToMainActivity(){
        val intent = Intent(this@LoginActivity,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object {
        const val TAG = "LoginActivity"
    }


}

