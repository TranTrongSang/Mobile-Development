package com.example.ex2

import android.os.Bundle
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
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.app.Activity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //final Button callButton = (Button) findViewById(R.id.callButton)
        setContentView(R.layout.activity_main)
//        setContent {
//            Ex2Theme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }
//        }
    }
    fun makePhoneCall(view: View) {
        val editTextPhoneNumber = findViewById<EditText>(R.id.phoneNumber)
        val phoneNumber = editTextPhoneNumber.text.toString().trim()

        if (!phoneNumber.isEmpty()) {
            // Kiểm tra xem số điện thoại có chứa ký tự đặc biệt nào không
            if (phoneNumber.matches("[0-9]+".toRegex())) {
                // Nếu là số điện thoại hợp lệ, thực hiện cuộc gọi
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:$phoneNumber")
                startActivity(intent)
            } else {
                // Nếu số điện thoại chứa ký tự đặc biệt, thông báo lỗi
                editTextPhoneNumber.error = "Invalid Phone Number"
            }
        } else {
            // Nếu số điện thoại trống, thông báo lỗi
            editTextPhoneNumber.error = "Please re-enter the Phone Number"
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ex2Theme {
        Greeting("Android")
    }
}