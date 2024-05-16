package com.example.ex2_lab04;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private Button buttonReadData;
    private Button buttonWriteData;
    private EditText editTextData;
    private static final String FILE_NAME = "data.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonReadData = findViewById(R.id.button_read_data);
        buttonWriteData = findViewById(R.id.button_save_data);
        editTextData = findViewById(R.id.edit_text_data);

        buttonReadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readDataFromFile();
            }
        });

        buttonWriteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeDataToFile();
            }
        });
    }

    private void readDataFromFile() {
        FileInputStream fis = null;

        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }

            editTextData.setText(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error reading file", Toast.LENGTH_SHORT).show();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void writeDataToFile() {
        String textToSave = editTextData.getText().toString();
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(textToSave.getBytes());
            Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error writing file", Toast.LENGTH_SHORT).show();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
