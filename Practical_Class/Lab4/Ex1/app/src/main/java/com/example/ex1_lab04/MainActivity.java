package com.example.ex1_lab04;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private Button btnReadData;
    private EditText etData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReadData = findViewById(R.id.btnReadData);
        etData = findViewById(R.id.etData);

        btnReadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readDataFromFile();
            }
        });
    }

    private void readDataFromFile() {
        InputStream inputStream = getResources().openRawResource(R.raw.myfile);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            etData.setText(stringBuilder.toString());
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            etData.setText("Error reading file.");
        }
    }
}
