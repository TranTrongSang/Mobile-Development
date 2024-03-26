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
        int backgroundColor = Color.BLACK; // Màu nền mặc định
        int textColor = Color.BLACK; // Màu chữ mặc định
        String colorName = "";

        if (colorId == R.id.buttonRed) {
            backgroundColor = Color.RED;
            colorName = "Red";
        } else if (colorId == R.id.buttonGreen) {
            backgroundColor = Color.GREEN;
            colorName = "Green";
        } else if (colorId == R.id.buttonBlue) {
            backgroundColor = Color.BLUE;
            textColor = Color.WHITE; // Nếu là màu Blue, chữ sẽ là màu trắng
            colorName = "Blue";
        } else if (colorId == R.id.buttonYellow) {
            backgroundColor = Color.YELLOW;
            colorName = "Yellow";
        }

        mainView.setBackgroundColor(backgroundColor);
        messageTextView.setText("Hello World, Now your Screen is " + colorName);
        messageTextView.setTextColor(textColor); // Đặt màu chữ cho TextView
    }
}
