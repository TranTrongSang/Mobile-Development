
package com.example.ex4_java;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        // Xử lý sự kiện khi người dùng nhấn nút giữa của điện thoại
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    displayAlertDialog();
                    return true;
                }
                return false;
            }
        });
    }


    // Hiển thị AlertDialog với nội dung là chuỗi văn bản từ EditText
    private void displayAlertDialog() {
        String userInput = editText.getText().toString().trim();
        if (!userInput.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Thông báo")
                    .setMessage("Chuỗi văn bản: " + userInput)
                    .setPositiveButton("Đóng", null)
                    .show();
        } else {
            Toast.makeText(this, "Vui lòng nhập dữ liệu", Toast.LENGTH_SHORT).show();
        }
    }
}
