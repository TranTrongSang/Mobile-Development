package com.example.ex3_java;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonShowTime = findViewById(R.id.button_show_time);
        buttonShowTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy ngày tháng hiện tại
                SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                String currentDate = sdfDate.format(date);

                // Hiển thị Toast với ngày tháng và thời gian
                Toast.makeText(MainActivity.this, "Ngày tháng: " + currentDate + " - Thời gian: " + getCurrentTime(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getCurrentTime() {
        // Lấy thời gian hiện hành
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return sdfTime.format(date);
    }
}

