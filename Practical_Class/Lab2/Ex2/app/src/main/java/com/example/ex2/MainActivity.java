package com.example.ex2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private View mainView;
    private TextView messageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainView = findViewById(android.R.id.content);
        messageTextView = findViewById(R.id.messageTextView);
    }

    public void changeColor(View view) {
        int colorId = view.getId();
        int color = Color.BLACK; // Màu mặc định
        String colorName = "";

        if (colorId == R.id.buttonRed) {
            color = Color.RED;
            colorName = "Red";
        } else if (colorId == R.id.buttonGreen) {
            color = Color.GREEN;
            colorName = "Green";
        } else if (colorId == R.id.buttonBlue) {
            color = Color.BLUE;
            colorName = "Blue";
        } else if (colorId == R.id.buttonYellow) {
            color = Color.YELLOW;
            colorName = "Yellow";
        }

        mainView.setBackgroundColor(color);
        messageTextView.setText("Hello World, Now your Screen is " + colorName);
    }
}
