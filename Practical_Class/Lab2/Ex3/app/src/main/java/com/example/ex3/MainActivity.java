package com.example.ex3;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display; // Reference to the EditText for displaying numbers
    private Button button7, button8, button9, buttonDivide; // All the buttons
    private Button button4, button5, button6, buttonMultiply; // All the buttons
    private Button button1, button2, button3, buttonSubtract; // All the buttons
    private Button button0, buttonEqual, buttonClear, buttonPlus; // All the buttons
    private double firstOperand, secondOperand, result;
    private String operator;
    private StringBuilder currentExpression = new StringBuilder();
    private boolean isNewExpression = true;

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
                case 'C':
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
    private double evaluateExpression(String expression) {
        double result;

        if (expression.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Expression is empty", Toast.LENGTH_SHORT).show();
            return 0;
        }

        if (expression.matches("^-?\\d+$")) {
            return Double.parseDouble(expression);
        }

        try {
            String[] tokens = expression.split("(?<=[-+*/])|(?=[-+*/])");

            if (tokens.length >= 3) {
                double operand1 = Double.parseDouble(tokens[0]);
                String operator = tokens[1];
                double operand2 = Double.parseDouble(tokens[2]);

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
                        if (operand2 != 0) {
                            result = operand1 / operand2;
                        } else {
                            Toast.makeText(getApplicationContext(), "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                            return 0;
                        }
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Invalid operator", Toast.LENGTH_SHORT).show();
                        return 0;
                }
            } else {
                Toast.makeText(getApplicationContext(), "Invalid expression", Toast.LENGTH_SHORT).show();
                return 0;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Invalid expression format", Toast.LENGTH_SHORT).show();
            return 0;
        }

        return result;
    }

}