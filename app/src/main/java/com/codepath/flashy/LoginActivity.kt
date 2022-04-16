package com.codepath.flashy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.parse.Parse
import com.parse.ParseObject
import com.parse.ParseUser
import com.parse.ParseException;
import com.parse.SignUpCallback;


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

        findViewById<Button>(R.id.bLogin).setOnClickListener {
            val username = findViewById<EditText>(R.id.etUserName).text.toString()
            val password = findViewById<EditText>(R.id.etPassword).text.toString()
            loginUser(username, password)

        }

        findViewById<Button>(R.id.bSignUp).setOnClickListener {
            val username = findViewById<EditText>(R.id.etUserName).text.toString()
            val password = findViewById<EditText>(R.id.etPassword).text.toString()
            signUpUser(username,password)
            findViewById<EditText>(R.id.etUserName).text.clear()
            findViewById<EditText>(R.id.etPassword).text.clear()


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
        val intent = Intent(this@LoginActivity, QuizActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object {
        const val TAG = "LoginActivity"
    }


}

