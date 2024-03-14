package com.example.ex3_java;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Lấy ID của Button từ layout
        final Button btn = findViewById(R.id.showTimeButton);
        final AlertDialog ad = new AlertDialog.Builder(this).create();

        // Xử lý sự kiện khi nút được nhấn
        btn.setOnClickListener(v -> {
            // Lấy thời gian hiện hành
//            String currentTime = getCurrentTime();

            // Hiển thị thông báo với thời gian hiện hành
            Toast.makeText(MainActivity.this, "Current Time: " + currentTime, Toast.LENGTH_SHORT).show();
        });
    }
//
//    // Phương thức để lấy thời gian hiện hành
//    private String getCurrentTime() {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
//        Date now = new Date();
//        return dateFormat.format(now);
    }
}
