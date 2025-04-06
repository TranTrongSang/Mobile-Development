package com.example.petrecognition;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Start with the splash screen
        Intent intent = new Intent(this, SplashScreen.class);
        startActivity(intent);
        finish();
    }
}

