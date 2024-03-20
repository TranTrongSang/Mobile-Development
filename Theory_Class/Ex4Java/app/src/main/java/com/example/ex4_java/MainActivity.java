
package com.example.ex4_java;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

     EditText etInput;
     Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = findViewById(R.id.et_input);
        btnSubmit = findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
            if (v == btnSubmit) {
                String inputData = etInput.getText().toString();
                Toast.makeText(MainActivity.this, inputData, Toast.LENGTH_SHORT).show();
        }
    }
}
