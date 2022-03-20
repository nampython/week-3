package com.example.android_challenge_w2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Onboarding1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding1)
        supportActionBar?.hide();

        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener{
            val intent = Intent(this, Onboarding2::class.java)
            startActivity(intent)
        }
    }
}