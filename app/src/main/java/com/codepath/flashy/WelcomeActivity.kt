package com.codepath.flashy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            finish()
        }, SPASH_TIME_OUT)
    }

    companion object{
        private const val SPASH_TIME_OUT = 5000L
    }
}