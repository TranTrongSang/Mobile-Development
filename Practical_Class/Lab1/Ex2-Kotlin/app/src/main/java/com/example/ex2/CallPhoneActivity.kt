package com.example.ex2

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class CallPhoneActivity : AppCompatActivity() {
    private lateinit var editTextPhone: EditText
    private lateinit var btnCall: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_phone)

        editTextPhone = findViewById(R.id.phoneNumber)
        btnCall = findViewById(R.id.callButton)

        // Click Call
        btnCall.setOnClickListener {
            // Declare hidden intent
            val callIntent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + editTextPhone.text.toString()))

            // User Permission Required
            if (ActivityCompat.checkSelfPermission(this@CallPhoneActivity, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this@CallPhoneActivity, arrayOf(android.Manifest.permission.CALL_PHONE), 1)
                return@setOnClickListener
            }
            startActivity(callIntent)
        }
    }
}
