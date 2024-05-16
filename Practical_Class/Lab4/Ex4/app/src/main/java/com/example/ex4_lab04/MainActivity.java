package com.example.ex4_lab04;


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
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView imageViewProfile;
    private EditText editTextName, editTextEmail, editTextPhone;
    private RadioGroup radioGroupGender;
    private Button buttonSave, buttonCancel;
    private File photoFile;

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
            photoFile = createImageFile();
            if (photoFile != null) {
                Uri photoURI = Uri.fromFile(photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap imageBitmap = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
            imageViewProfile.setImageBitmap(imageBitmap);
        }
    }

    private File createImageFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(
                    imageFileName,
                    ".jpg",
                    storageDir
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    private void saveUserProfile() {
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String phone = editTextPhone.getText().toString();
        int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
        RadioButton selectedGenderButton = findViewById(selectedGenderId);
        String gender = selectedGenderButton.getText().toString();

        String userProfile = name + "\n" + email + "\n" + phone + "\n" + gender;

        File file = new File(getExternalFilesDir(null), "user_profile.txt");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(userProfile);
            Toast.makeText(this, "Profile saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to save profile", Toast.LENGTH_SHORT).show();
        }

        if (photoFile != null && photoFile.exists()) {
            File destFile = new File(getExternalFilesDir(null), "profile_image.jpg");
            photoFile.renameTo(destFile);
        }
    }

    private void loadUserProfile() {
        File file = new File(getExternalFilesDir(null), "user_profile.txt");
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

        File imageFile = new File(getExternalFilesDir(null), "profile_image.jpg");
        if (imageFile.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
            imageViewProfile.setImageBitmap(bitmap);
        }
    }
}
