package com.example.ex4;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextProject;
    private Button buttonViewContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextProject = findViewById(R.id.editTextProject);

        Button buttonViewContact = findViewById(R.id.buttonViewContactInfo);
        buttonViewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                String project = editTextProject.getText().toString();

                Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("project", project);
                startActivity(intent);
            }
        });
    }
}
