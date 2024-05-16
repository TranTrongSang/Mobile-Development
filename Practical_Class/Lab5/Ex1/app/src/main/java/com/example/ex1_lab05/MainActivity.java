package com.example.ex1_lab05;
import androidx.appcompat.app.AppCompatActivity;



import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;
import java.util.Random;




public class MainActivity extends AppCompatActivity {

    // Key for saving the state of the TextView
    private static final String TEXT_STATE = "currentText";

    // The TextView where we will show results
    private TextView mTextView = null;
    private Button mStartButton;
    private Button mResetButton;
    private SimpleAsyncTask mSimpleAsyncTask;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize mTextView, mStartButton, and mResetButton
        mTextView = findViewById(R.id.textView1);
        mStartButton = findViewById(R.id.startButton);
        mResetButton = findViewById(R.id.resetButton);
        mHandler = new Handler();

        // Restore TextView if there is a savedInstanceState
        if (savedInstanceState != null) {
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
        } else {
            mTextView.setText("Press \"Start Task\" button to create a new Task");
        }

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTask();
            }
        });

        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTask();
            }
        });
    }

    private void startTask() {
        mSimpleAsyncTask = new SimpleAsyncTask();
        mSimpleAsyncTask.execute();
    }

    private void resetTask() {
        if (mSimpleAsyncTask != null && !mSimpleAsyncTask.isCancelled()) {
            mSimpleAsyncTask.cancel(true); // Cancel the ongoing task
        }
        // Reset the TextView to the initial message
        mTextView.setText("Press \"Start Task\" button to create a new Task");
        // Enable the start button if it was disabled
        mStartButton.setEnabled(true);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the state of the TextView
        outState.putString(TEXT_STATE, mTextView.getText().toString());
    }

    private class SimpleAsyncTask extends AsyncTask<Void, String, String> {

        @Override
        protected void onPreExecute() {
            // Disable the start button to prevent multiple tasks from starting simultaneously
            mStartButton.setEnabled(false);
        }

        @Override
        protected String doInBackground(Void... voids) {
            // Sleep for a random amount of time (between 10 to 50 minutes)
            int sleepTime = new Random().nextInt(41) + 10; // Random between 10 and 50
            long sleepTimeSeconds = sleepTime * 60; // Convert minutes to seconds

            for (long i = sleepTimeSeconds; i >= 0; i--) { // Update every second
                if (isCancelled()) {
                    return null; // Exit if the task is cancelled
                }
                try {
                    Thread.sleep(1000); // Sleep for 1000 milliseconds (1 second)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long hours = i / 3600;
                long minutes = (i % 3600) / 60;
                long seconds = i % 60;
                publishProgress(String.format("Task running... %d hours %d minutes %d seconds remaining", hours, minutes, seconds));
            }

            return String.format("Task completed after sleeping for %d minutes", sleepTime);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            mTextView.setText(values[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                mTextView.setText(result);
            }
            mStartButton.setEnabled(true);
        }

        @Override
        protected void onCancelled() {
            mTextView.setText("Task was cancelled");
            mStartButton.setEnabled(true);

            // Delay 3 seconds before resetting the TextView
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mTextView.setText("Press \"Start Task\" button to create a new Task");
                }
            }, 3000); // 3000 milliseconds = 3 seconds
        }
    }
}


