package com.example.ex5_java;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


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

//public class MainActivity extends AppCompatActivity {
//
//    private TextView display; // Reference to the EditText for displaying numbers
//    private Button button7, button8, button9, buttonDivide; // All the buttons
//    private Button button4, button5, button6, buttonMultiply; // All the buttons
//    private Button button1, button2, button3, buttonSubtract; // All the buttons
//    private Button button0, buttonEqual, buttonClear, buttonPlus; // All the buttons
//    private int firstOperand, secondOperand; // Variables to store operands
//    private String operator; // Variable to store the selected operator
//    private StringBuilder currentExpression = new StringBuilder(); // Variable to store current expression
//    private boolean isNewExpression = true; // Flag to indicate whether a new expression is being entered
//    private char lastOperator;
//    private int result;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // Find all the views from the layout
//        display = findViewById(R.id.display);
//        button7 = findViewById(R.id.button7);
//        button8 = findViewById(R.id.button8);
//        button9 = findViewById(R.id.button9);
//        buttonDivide = findViewById(R.id.buttonDivide);
//        button4 = findViewById(R.id.button4);
//        button5 = findViewById(R.id.button5);
//        button6 = findViewById(R.id.button6);
//        buttonMultiply = findViewById(R.id.buttonMultiply);
//        button1 = findViewById(R.id.button1);
//        button2 = findViewById(R.id.button2);
//        button3 = findViewById(R.id.button3);
//        buttonSubtract = findViewById(R.id.buttonSubtract);
//        button0 = findViewById(R.id.button0);
//        buttonEqual = findViewById(R.id.button_equal);
//        buttonClear = findViewById(R.id.buttonClear);
//        buttonPlus = findViewById(R.id.buttonAdd);
//
//        // Set click listeners for each button
//        button0.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                display.append("0"); // Update the display with the button text
//            }
//        });
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                display.append("1"); // Update the display with the button text
//            }
//        });
//
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                display.append("2"); // Update the display with the button text
//            }
//        });
//
//        button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                display.append("3"); // Update the display with the button text
//            }
//        });
//        button4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                display.append("4"); // Update the display with the button text
//            }
//        });
//        button5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                display.append("5"); // Update the display with the button text
//            }
//        });
//
//        button6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                display.append("6"); // Update the display with the button text
//            }
//        });
//        button7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                display.append("7"); // Update the display with the button text
//            }
//        });
//
//        button8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                display.append("8");
//            }
//        });
//
//        button9.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                display.append("9"); // Update the display with the button text
//            }
//        });
//
//        buttonClear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String currentText = display.getText().toString();
//                if (!currentText.isEmpty()) {
//                    String newText = currentText.substring(0, currentText.length() - 1);
//                    display.setText(newText);
//                }
//            }
//        });
//
//        buttonDivide.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                operator = "/";
//                firstOperand = Integer.parseInt(display.getText().toString()); // Convert display text to double
//                display.setText(""); // Clear the display for next input
//            }
//        });
//
//        buttonMultiply.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                operator = "*";
//                firstOperand = Integer.parseInt(display.getText().toString());
//                display.setText("");
//            }
//        });
//
//        buttonSubtract.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                operator = "-";
//                firstOperand = Integer.parseInt(display.getText().toString());
//                display.setText("");
//            }
//        });
//
//        buttonPlus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                operator = "+";
//                firstOperand = Integer.parseInt(display.getText().toString());
//                display.setText("");
//            }
//        });
//
//
//        buttonEqual.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                secondOperand = Integer.parseInt(display.getText().toString());
//                int result;
//                switch (operator) {
//                    case "/":
//                        result = firstOperand / secondOperand;
//                        break;
//                    case "*":
//                        result = firstOperand * secondOperand;
//                        break;
//                    case "-":
//                        result = firstOperand - secondOperand;
//                        break;
//                    case "+":
//                        result = firstOperand + secondOperand;
//                        break;
//                    default:
//                        result = 0; // Handle invalid operator case (optional)
//                }
//                display.setText(String.valueOf(result)); // Update display with the result
//            }
//        });
//
//
//    }
//}


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display; // Reference to the TextView for displaying numbers
    private Button button7, button8, button9, buttonDivide, button4, button5, button6, buttonMultiply, button1, button2, button3, buttonSubtract, button0, buttonEqual, buttonClear, buttonPlus; // References to all buttons
    private StringBuilder currentExpression = new StringBuilder(); // Variable to store current expression
    private boolean isNewExpression = true; // Flag to indicate whether a new expression is being entered
    private double result; // Variable to store result of calculation
    private char lastOperator; // Variable to store the last operator used

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
        buttonClear = findViewById(R.id.buttonClear);
        buttonPlus = findViewById(R.id.buttonAdd);

        // Set click listeners for each button
        button0.setOnClickListener(onClickListener);
        button1.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);
        button3.setOnClickListener(onClickListener);
        button4.setOnClickListener(onClickListener);
        button5.setOnClickListener(onClickListener);
        button6.setOnClickListener(onClickListener);
        button7.setOnClickListener(onClickListener);
        button8.setOnClickListener(onClickListener);
        button9.setOnClickListener(onClickListener);
        buttonDivide.setOnClickListener(onClickListener);
        buttonMultiply.setOnClickListener(onClickListener);
        buttonSubtract.setOnClickListener(onClickListener);
        buttonPlus.setOnClickListener(onClickListener);
        buttonEqual.setOnClickListener(onClickListener);
        buttonClear.setOnClickListener(onClickListener);
    }

    // OnClickListener for all buttons
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            String buttonText = button.getText().toString();
            char operator = buttonText.charAt(0);

            switch (operator) {
                case '+':
                case '-':
                case '*':
                case '/':
                    if (!isNewExpression) {
                        currentExpression.append(' ').append(buttonText).append(' ');
                    } else {
                        isNewExpression = false;
                    }
                    break;
                case '=':
                    isNewExpression = true;
                    try {
                        result = evaluateExpression(currentExpression.toString());
                        display.setText(String.valueOf(result));
                        currentExpression.setLength(0);
                        currentExpression.append(result);
                    } catch (Exception e) {
                        display.setText("Error");
                    }
                    break;
                case 'C': // Clear button
                    isNewExpression = true;
                    currentExpression.setLength(0);
                    display.setText("");
                    break;
                default:
                    if (isNewExpression) {
                        currentExpression.setLength(0);
                    }
                    currentExpression.append(buttonText);
                    isNewExpression = false;
                    break;
            }

            display.setText(currentExpression.toString());
        }
    };

    // Method to evaluate expression

    // Trong phương thức evaluateExpression, thêm mã xử lý cho các trường hợp ngoại lệ
    private double evaluateExpression(String expression) {
        double result = 0;

        // Kiểm tra xem biểu thức có rỗng không
        if (expression.isEmpty()) {
            // Nếu biểu thức rỗng, hiển thị thông báo Toast và trả về 0
            Toast.makeText(getApplicationContext(), "Expression is empty", Toast.LENGTH_SHORT).show();
            return 0;
        }

        // Kiểm tra xem biểu thức chỉ chứa số không
        if (expression.matches("^-?\\d+$")) {
            // Nếu biểu thức chỉ chứa số, trả về số âm nếu có dấu trừ đằng trước, hoặc số dương nếu không có dấu
            return Double.parseDouble(expression);
        }

        try {
            // Tách biểu thức thành các thành phần: số hạng và toán tử
            String[] tokens = expression.split("(?<=[-+*/])|(?=[-+*/])");

            // Kiểm tra xem biểu thức có ít nhất 3 phần tử không (số hạng thứ nhất, toán tử, số hạng thứ hai)
            if (tokens.length >= 3) {
                // Lấy số hạng thứ nhất từ tokens[0]
                double operand1 = Double.parseDouble(tokens[0]);

                // Lấy toán tử từ tokens[1]
                String operator = tokens[1];

                // Lấy số hạng thứ hai từ tokens[2]
                double operand2 = Double.parseDouble(tokens[2]);

                // Thực hiện phép tính theo toán tử
                switch (operator) {
                    case "+":
                        result = operand1 + operand2;
                        break;
                    case "-":
                        result = operand1 - operand2;
                        break;
                    case "*":
                        result = operand1 * operand2;
                        break;
                    case "/":
                        // Kiểm tra xem số hạng thứ hai có khác 0 không để tránh lỗi chia cho 0
                        if (operand2 != 0) {
                            result = operand1 / operand2;
                        } else {
                            // Nếu số hạng thứ hai bằng 0, hiển thị thông báo Toast và trả về 0
                            Toast.makeText(getApplicationContext(), "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                            return 0;
                        }
                        break;
                    default:
                        // Nếu toán tử không hợp lệ, hiển thị thông báo Toast và trả về 0
                        Toast.makeText(getApplicationContext(), "Invalid operator", Toast.LENGTH_SHORT).show();
                        return 0;
                }
            } else {
                // Nếu biểu thức không có đủ các thành phần, hiển thị thông báo Toast và trả về 0
                Toast.makeText(getApplicationContext(), "Invalid expression", Toast.LENGTH_SHORT).show();
                return 0;
            }
        } catch (NumberFormatException e) {
            // Nếu có lỗi khi chuyển đổi chuỗi sang số, hiển thị thông báo Toast và trả về 0
            Toast.makeText(getApplicationContext(), "Invalid expression format", Toast.LENGTH_SHORT).show();
            return 0;
        }

        return result;
    }


}


