package com.example.ex4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact);
        TextView textViewName = findViewById(R.id.textViewName);
        TextView textViewEmail = findViewById(R.id.textViewEmail);
        TextView textViewProject = findViewById(R.id.textViewProject);

// Lấy dữ liệu từ Intent mà bạn chuyển từ MainActivity và hiển thị nó trong TextView
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String project = intent.getStringExtra("project");

        textViewName.setText("Name: " + name);
        textViewEmail.setText("Email: " + email);
        textViewProject.setText("Project: " + project);


    }
}