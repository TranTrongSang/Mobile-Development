package com.example.ex5;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btnShowFilter);
        textView = findViewById(R.id.textView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get a random intent
                Intent randomIntent = getRandomIntent();
                // Display the random intent on the text view
                displayIntent(randomIntent);
                // Start the random intent
                startActivity(randomIntent);
            }
        });
    }

    private Intent getRandomIntent() {
        Intent[] intents = {
                createCallIntent(),
                createSendToIntent(),
                createViewImageIntent(),
                createViewMusicIntent(),
                createMapIntent(),
//                createCustomIntent1(),
//                createCustomIntent2(),
//                createCustomIntent3()
        };

        Random random = new Random();
        int index = random.nextInt(intents.length);
        return intents[index];
    }

    private Intent createCallIntent() {
        return new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0123456789"));
    }

    private Intent createSendToIntent() {
        return new Intent(Intent.ACTION_SENDTO);
    }

    private Intent createViewImageIntent() {
        return new Intent(Intent.ACTION_VIEW, Uri.parse("content://media/external/images/media"));
    }

    private Intent createViewMusicIntent() {
        return new Intent(Intent.ACTION_VIEW, Uri.parse("content://media/external/audio/media"));
    }

    private Intent createMapIntent() {
        String url = "http://maps.google.com/maps?" +
                "saddr=9.938083,-84.054430&daddr=9.926392,-84.055964";
        return new Intent(Intent.ACTION_VIEW, Uri.parse(url));
    }

     private Intent createCustomIntent1() {
        // Define your custom intent with permission here
        return null;
    }

    private Intent createCustomIntent2() {
        // Define your custom intent with permission here
        return null;
    }

    private Intent createCustomIntent3() {
        // Define your custom intent with permission here
        return null;
    }

    private void displayIntent(Intent intent) {
        textView.setText(intent.getAction());
    }
}
