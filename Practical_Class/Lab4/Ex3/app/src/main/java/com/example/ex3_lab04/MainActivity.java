package com.example.ex3_lab04;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView imageViewProfile;
    private EditText editTextName, editTextEmail, editTextPhone;
    private RadioGroup radioGroupGender;
    private Button buttonSave, buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewProfile = findViewById(R.id.imageViewProfile);
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        buttonSave = findViewById(R.id.buttonSave);
        buttonCancel = findViewById(R.id.buttonCancel);

        Button buttonChangePhoto = findViewById(R.id.buttonChangePhoto);
        buttonChangePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserProfile();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loadUserProfile();
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageViewProfile.setImageBitmap(imageBitmap);
            saveImageToInternalStorage(imageBitmap);
        }
    }

    private void saveImageToInternalStorage(Bitmap bitmap) {
        File file = new File(getFilesDir(), "profile_image.png");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            Toast.makeText(this, "Image saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error saving image", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveUserProfile() {
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String phone = editTextPhone.getText().toString();
        int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
        RadioButton selectedGenderButton = findViewById(selectedGenderId);
        String gender = selectedGenderButton.getText().toString();

        String userProfile = name + "\n" + email + "\n" + phone + "\n" + gender;

        File file = new File(getFilesDir(), "user_profile.txt");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(userProfile.getBytes());
            Toast.makeText(this, "Profile saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error saving profile", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadUserProfile() {
        File file = new File(getFilesDir(), "user_profile.txt");
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
                String name = br.readLine();
                String email = br.readLine();
                String phone = br.readLine();
                String gender = br.readLine();

                editTextName.setText(name);
                editTextEmail.setText(email);
                editTextPhone.setText(phone);
                if (gender.equals("Female")) {
                    radioGroupGender.check(R.id.radioButtonFemale);
                } else {
                    radioGroupGender.check(R.id.radioButtonMale);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Load the profile image
        File imageFile = new File(getFilesDir(), "profile_image.png");
        if (imageFile.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
            imageViewProfile.setImageBitmap(bitmap);
        }
    }
}
