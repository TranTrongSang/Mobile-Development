package com.example.ex5_java;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        // Bắt sự kiện click cho từng nút số và phép toán
        int[] buttonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9, R.id.buttonDecimal,
                R.id.buttonClear, R.id.buttonBackspace, R.id.buttonAdd,
                R.id.buttonSubtract, R.id.buttonMultiply, R.id.buttonDivide,
                R.id.buttonEqual
        };

        for (int id : buttonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onButtonClick(v);
                }
            });
        }
    }

    // Xử lý sự kiện khi người dùng click nút
    public void onButtonClick(View v) {
        String text = display.getText().toString();
        Button button = (Button) v;
        String buttonText = button.getText().toString();

        switch (v.getId()) {
            case R.id.button_clear:
                // Xóa mọi nội dung trên màn hình
                display.setText("");
                break;
            case R.id.button_backspace:
                // Xóa ký tự cuối cùng
                if (text.length() > 0) {
                    display.setText(text.substring(0, text.length() - 1));
                }
                break;
            case R.id.button_equal:
                // Tính toán kết quả biểu thức
                try {
                    String result = evaluateExpression(text);
                    display.setText(result);
                } catch (Exception e) {
                    display.setText("Error");
                }
                break;
            default:
                // Thêm ký tự tương ứng vào màn hình
                display.setText(text + buttonText);
                break;
        }
    }

    // Phương thức tính toán biểu thức
    private String evaluateExpression(String expression) {
        // Viết logic tính toán biểu thức ở đây
        // Ví dụ đơn giản:
        // return String.valueOf(new ScriptEngineManager().getEngineByName("rhino").eval(expression));
        return "";
    }
}
