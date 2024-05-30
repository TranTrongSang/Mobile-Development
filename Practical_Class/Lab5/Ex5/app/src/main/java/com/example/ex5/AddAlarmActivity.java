package com.example.ex5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;

public class AddAlarmActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private EditText alarmLabel;
    private CheckBox mondayCheckBox;
    // Add other CheckBoxes for other days of the week
    private Button saveAlarmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);

        timePicker = findViewById(R.id.timePicker);
        alarmLabel = findViewById(R.id.alarmLabel);
        mondayCheckBox = findViewById(R.id.mondayCheckBox);
        // Initialize other CheckBoxes for other days of the week
        saveAlarmButton = findViewById(R.id.saveAlarmButton);

        saveAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the time from the TimePicker
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                // Get the label from the EditText
                String label = alarmLabel.getText().toString();

                // Get the selected days
                boolean isMondayChecked = mondayCheckBox.isChecked();
                // Check other days similarly

                // TODO: Save the alarm details
            }
        });
    }
}
