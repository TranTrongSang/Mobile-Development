package com.example.ex5_java;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//public class MainActivity extends AppCompatActivity {
//
//    private EditText display;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        display = findViewById(R.id.display);
//
//        // Bắt sự kiện click cho từng nút số và phép toán
//        int[] buttonIds = {
//                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
//                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
//                R.id.button8, R.id.button9, R.id.buttonDecimal,
//                R.id.buttonClear, R.id.buttonBackspace, R.id.buttonAdd,
//                R.id.buttonSubtract, R.id.buttonMultiply, R.id.buttonDivide,
//                R.id.buttonEqual
//        };
//
//        for (int id : buttonIds) {
//            Button button = findViewById(id);
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onButtonClick(v);
//                }
//            });
//        }
//    }
//
//    // Xử lý sự kiện khi người dùng click nút
//    public void onButtonClick(View v) {
//        String text = display.getText().toString();
//        Button button = (Button) v;
//        String buttonText = button.getText().toString();
//
//        switch (v.getId()) {
//            case R.id.button_clear:
//                // Xóa mọi nội dung trên màn hình
//                display.setText("");
//                break;
//            case R.id.button_backspace:
//                // Xóa ký tự cuối cùng
//                if (text.length()  0) {
//                    display.setText(text.substring(0, text.length() - 1));
//                }
//                break;
//            case R.id.button_equal:
//                // Tính toán kết quả biểu thức
//                try {
//                    String result = evaluateExpression(text);
//                    display.setText(result);
//                } catch (Exception e) {
//                    display.setText("Error");
//                }
//                break;
//            default:
//                // Thêm ký tự tương ứng vào màn hình
//                display.setText(text + buttonText);
//                break;
//        }
//    }
//
//    // Phương thức tính toán biểu thức
//    private String evaluateExpression(String expression) {
//        // Viết logic tính toán biểu thức ở đây
//        // Ví dụ đơn giản:
//        // return String.valueOf(new ScriptEngineManager().getEngineByName("rhino").eval(expression));
//        return "";
//    }
//}

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display; // Reference to the EditText for displaying numbers
    private Button button7, button8, button9, buttonDivide, button4, button5, button6, buttonMultiply, button1, button2, button3, buttonSubtract, button0, buttonEqual, buttonClear, buttonPlus; // References to all buttons
    private double firstOperand, secondOperand; // Variables to store operands
    private String operator; // Variable to store the selected operator
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find all the views from the layout
        display = findViewById(R.id.display);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonDivide = findViewById(R.id.buttonDivide);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        buttonMultiply = findViewById(R.id.buttonMultiply);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        buttonSubtract = findViewById(R.id.buttonSubtract);
        button0 = findViewById(R.id.button0);
        buttonEqual = findViewById(R.id.button_equal);
        buttonClear = findViewById(R.id.button_backspace);
        buttonPlus = findViewById(R.id.buttonAdd);

        // Set click listeners for each button
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.append("0"); // Update the display with the button text
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.append("1"); // Update the display with the button text
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.append("2"); // Update the display with the button text
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.append("3"); // Update the display with the button text
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.append("4"); // Update the display with the button text
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.append("5"); // Update the display with the button text
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.append("6"); // Update the display with the button text
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.append("7"); // Update the display with the button text
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.append("8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.append("9"); // Update the display with the button text
            }
        });
//        buttonPlus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                display.append("+"); // Update the display with the button text
//            }
//        });
//        buttonSubtract.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                display.append("-"); // Update the display with the button text
//            }
//        });
//        buttonDivide.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                display.append("/"); // Update the display with the button text
//            }
//        });
//
//        buttonMultiply.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                display.append("*"); // Update the display with the button text
//            }
//        });


        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operator = "/";
                firstOperand = Double.parseDouble(display.getText().toString()); // Convert display text to double
                display.setText(""); // Clear the display for next input
            }
        });

        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operator = "*";
                firstOperand = Double.parseDouble(display.getText().toString());
                display.setText("");
            }
        });

        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operator = "-";
                firstOperand = Double.parseDouble(display.getText().toString());
                display.setText("");
            }
        });

        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operator = "+";
                firstOperand = Double.parseDouble(display.getText().toString());
                display.setText("");
            }
        });
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondOperand = Double.parseDouble(display.getText().toString());
                double result;
                switch (operator) {
                    case "/":
                        result = firstOperand / secondOperand;
                        break;
                    case "*":
                        result = firstOperand * secondOperand;
                        break;
                    case "-":
                        result = firstOperand - secondOperand;
                        break;
                    case "+":
                        result = firstOperand + secondOperand;
                        break;
                    default:
                        result = 0; // Handle invalid operator case (optional)
                }
                display.setText(String.valueOf(result)); // Update display with the result
            }
        });


    }
}


