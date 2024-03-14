package com.example.ex2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ex2.ui.theme.Ex2Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val callButton: Button = findViewById(R.id.callButton)
        val phoneNumber: EditText = findViewById(R.id.phoneNumber)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callButton.setOnClickListener{
            val myIntent = Intent(this@MainActivity, CallPhoneActivity::class.java)
            startActivity(myIntent)
        }
    }
}

