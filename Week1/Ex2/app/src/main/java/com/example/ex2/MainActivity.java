package com.example.ex2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "SentimentAnalyzer";
    private EditText inputText;
    private Button submitButton;
    private ImageView emojiView;
    private ConstraintLayout mainLayout;
    private RequestQueue requestQueue;

    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=AIzaSyAoIrdYJnEnQV140otBawzJjROSYOMgwtI";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        inputText = findViewById(R.id.inputText);
        submitButton = findViewById(R.id.submitButton);
        emojiView = findViewById(R.id.emojiView);
        mainLayout = findViewById(R.id.mainLayout);

        // Initialize request queue
        requestQueue = Volley.newRequestQueue(this);

        // Set up button click listener
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = inputText.getText().toString().trim();
                if (!text.isEmpty()) {
                    analyzeSentiment(text);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter some text", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void analyzeSentiment(String text) {
        try {
            // Create the parts JSON array with a single text object
            JSONObject textPart = new JSONObject();
            textPart.put("text", "Analyze the sentiment of this text and respond with only one word: POSITIVE or NEGATIVE: \"" + text + "\"");

            JSONArray parts = new JSONArray();
            parts.put(textPart);

            // Create the content object
            JSONObject content = new JSONObject();
            content.put("role", "user");
            content.put("parts", parts);

            // Put content in contents array
            JSONArray contents = new JSONArray();
            contents.put(content);

            // Create the generation config
            JSONObject generationConfig = new JSONObject();
            generationConfig.put("temperature", 0);

            // Create the full request body
            JSONObject requestBody = new JSONObject();
            requestBody.put("contents", contents);
            requestBody.put("generationConfig", generationConfig);

            Log.d(TAG, "Request Body: " + requestBody.toString());

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, API_URL, requestBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d(TAG, "Full API Response: " + response.toString());
                            try {
                                JSONArray candidates = response.optJSONArray("candidates");
                                if (candidates != null && candidates.length() > 0) {
                                    JSONObject firstCandidate = candidates.optJSONObject(0);
                                    if (firstCandidate != null) {
                                        JSONObject content = firstCandidate.optJSONObject("content");
                                        if (content != null) {
                                            JSONArray parts = content.optJSONArray("parts");
                                            if (parts != null && parts.length() > 0) {
                                                JSONObject firstPart = parts.optJSONObject(0);
                                                if (firstPart != null) {
                                                    String responseText = firstPart.optString("text", "");
                                                    Log.d(TAG, "Extracted text: " + responseText);

                                                    if (responseText.toUpperCase().contains("POSITIVE")) {
                                                        displayPositiveSentiment();
                                                    } else if (responseText.toUpperCase().contains("NEGATIVE")) {
                                                        displayNegativeSentiment();
                                                    } else {
                                                        Toast.makeText(MainActivity.this, "Could not determine sentiment: " + responseText, Toast.LENGTH_LONG).show();
                                                    }
                                                } else {
                                                    Log.e(TAG, "First part is null");
                                                    Toast.makeText(MainActivity.this, "Error: first part is null", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                Log.e(TAG, "Parts array is null or empty");
                                                Toast.makeText(MainActivity.this, "Error: parts array is null or empty", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Log.e(TAG, "Content is null");
                                            Toast.makeText(MainActivity.this, "Error: content is null", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Log.e(TAG, "First candidate is null");
                                        Toast.makeText(MainActivity.this, "Error: first candidate is null", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Log.e(TAG, "Candidates array is null or empty");
                                    Toast.makeText(MainActivity.this, "Error: candidates array is null or empty", Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                Log.e(TAG, "Error parsing response", e);
                                Toast.makeText(MainActivity.this, "Error parsing response: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            String errorMsg = "Error: ";
                            if (error.networkResponse != null && error.networkResponse.data != null) {
                                try {
                                    String errorString = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                                    Log.e(TAG, "Error response: " + errorString);
                                    errorMsg += errorString;
                                } catch (Exception e) {
                                    errorMsg += "Could not parse error response";
                                }
                            } else {
                                errorMsg += error.getMessage();
                            }
                            Log.e(TAG, errorMsg, error);
                            Toast.makeText(MainActivity.this, errorMsg, Toast.LENGTH_LONG).show();
                        }
                    });

            // Add request to queue
            requestQueue.add(request);
        } catch (JSONException e) {
            Log.e(TAG, "Error creating request", e);
            Toast.makeText(this, "Error creating request: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void displayPositiveSentiment() {
        mainLayout.setBackgroundColor(getResources().getColor(R.color.positive_green));
        emojiView.setImageResource(R.drawable.happy_emoji);
        Toast.makeText(this, "Positive sentiment detected!", Toast.LENGTH_SHORT).show();
    }

    private void displayNegativeSentiment() {
        mainLayout.setBackgroundColor(getResources().getColor(R.color.negative_red));
        emojiView.setImageResource(R.drawable.sad_emoji);
        Toast.makeText(this, "Negative sentiment detected!", Toast.LENGTH_SHORT).show();
    }
}