package com.example.petrecognition;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.google.common.util.concurrent.ListenableFuture;

import org.tensorflow.lite.Interpreter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

import android.content.res.AssetManager;
import android.content.res.AssetFileDescriptor;
import android.util.Log;

import androidx.camera.view.PreviewView;

public class Home extends AppCompatActivity {

    private static final int REQUEST_CAMERA_PERMISSION = 100;
    private static final int REQUEST_GALLERY_IMAGE = 200;
    private static final String TAG = "PetRecognition";

    private Button btnCamera, btnGallery, btnUsePhoto, btnRetake;
    private ImageView imagePreview;
    private TextView resultText;
    private FrameLayout resultContainer;
    private LinearLayout buttonContainer;

    private PreviewView previewView;
    private Button btnCapture;

    private ImageCapture imageCapture;
    private File outputFile;
    private Bitmap capturedImage;
    private Interpreter tflite;

    private ProcessCameraProvider cameraProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize views
        btnCamera = findViewById(R.id.btnCamera);
        btnGallery = findViewById(R.id.btnGallery);
        btnUsePhoto = findViewById(R.id.btnUsePhoto);
        btnRetake = findViewById(R.id.btnRetake);
        btnCapture = findViewById(R.id.btnCapture);
        imagePreview = findViewById(R.id.imagePreview);
        previewView = findViewById(R.id.previewView);
        resultText = findViewById(R.id.resultText);
        resultContainer = findViewById(R.id.resultContainer);
        buttonContainer = findViewById(R.id.buttonContainer);

        // Initially hide confirmation buttons and capture button
        btnUsePhoto.setVisibility(View.GONE);
        btnRetake.setVisibility(View.GONE);
        btnCapture.setVisibility(View.GONE);
        previewView.setVisibility(View.GONE);
        resultContainer.setVisibility(View.GONE);

        // Load TensorFlow Lite model
        try {
            tflite = new Interpreter(loadModelFile());
            Log.d(TAG, "TensorFlow Lite model loaded successfully");
        } catch (IOException e) {
            Log.e(TAG, "Error loading model: " + e.getMessage(), e);
            Toast.makeText(this, "Không thể tải mô hình TensorFlow: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        // Set click listeners
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkCameraPermission()) {
                    btnCamera.setVisibility(View.GONE);
                    btnGallery.setVisibility(View.GONE);
                    previewView.setVisibility(View.VISIBLE);
                    imagePreview.setVisibility(View.GONE);
                    resultContainer.setVisibility(View.GONE);
                    openCamera();
                } else {
                    requestCameraPermission();
                }
            }
        });

        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureImage();
            }
        });

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        btnUsePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (capturedImage != null) {
                    recognizeImage(capturedImage);
                    showMainButtons();
                }
            }
        });

        btnRetake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previewView.setVisibility(View.VISIBLE);
                imagePreview.setVisibility(View.GONE);
                resultContainer.setVisibility(View.GONE);
                openCamera();
            }
        });
    }

    private boolean checkCameraPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                REQUEST_CAMERA_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(this, "Cần quyền truy cập camera để sử dụng tính năng này", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openCamera() {
        // Setup camera
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this);

        cameraProviderFuture.addListener(() -> {
            try {
                cameraProvider = cameraProviderFuture.get();

                Preview preview = new Preview.Builder().build();
                preview.setSurfaceProvider(previewView.getSurfaceProvider());

                imageCapture = new ImageCapture.Builder()
                        .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                        .build();

                CameraSelector cameraSelector = new CameraSelector.Builder()
                        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                        .build();

                cameraProvider.unbindAll();
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture);

                // Show capture button
                btnCapture.setVisibility(View.VISIBLE);

            } catch (ExecutionException | InterruptedException e) {
                Log.e(TAG, "Error opening camera: " + e.getMessage(), e);
                Toast.makeText(this, "Lỗi khi mở camera: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, ContextCompat.getMainExecutor(this));
    }

    private void captureImage() {
        if (imageCapture == null) {
            Log.e(TAG, "imageCapture is null");
            return;
        }

        // Take a picture
        outputFile = new File(getExternalCacheDir(), "photo.jpg");
        ImageCapture.OutputFileOptions outputOptions =
                new ImageCapture.OutputFileOptions.Builder(outputFile).build();

        imageCapture.takePicture(outputOptions, ContextCompat.getMainExecutor(this),
                new ImageCapture.OnImageSavedCallback() {
                    @Override
                    public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                        capturedImage = BitmapFactory.decodeFile(outputFile.getAbsolutePath());
                        imagePreview.setImageBitmap(capturedImage);
                        previewView.setVisibility(View.GONE);
                        imagePreview.setVisibility(View.VISIBLE);
                        showConfirmationButtons();
                        btnCapture.setVisibility(View.GONE);
                        // Clear previous result
                        resultText.setText("");
                        resultContainer.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(@NonNull ImageCaptureException exception) {
                        Log.e(TAG, "Error capturing image: " + exception.getMessage(), exception);
                        Toast.makeText(Home.this, "Lỗi khi chụp ảnh: " + exception.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_GALLERY_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GALLERY_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                imagePreview.setImageBitmap(bitmap);
                // Clear previous result before recognizing new image
                resultText.setText("");
                resultContainer.setVisibility(View.GONE);
                recognizeImage(bitmap);
            } catch (IOException e) {
                Log.e(TAG, "Error loading gallery image: " + e.getMessage(), e);
                Toast.makeText(this, "Lỗi khi tải ảnh", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showConfirmationButtons() {
        btnCamera.setVisibility(View.GONE);
        btnGallery.setVisibility(View.GONE);
        btnCapture.setVisibility(View.GONE);
        btnUsePhoto.setVisibility(View.VISIBLE);
        btnRetake.setVisibility(View.VISIBLE);
    }

    private void showMainButtons() {
        btnCamera.setVisibility(View.VISIBLE);
        btnGallery.setVisibility(View.VISIBLE);
        btnCapture.setVisibility(View.GONE);
        btnUsePhoto.setVisibility(View.GONE);
        btnRetake.setVisibility(View.GONE);
        previewView.setVisibility(View.GONE);
        imagePreview.setVisibility(View.VISIBLE);
    }

    private MappedByteBuffer loadModelFile() throws IOException {
        try {
            AssetManager assetManager = getAssets();
            AssetFileDescriptor fileDescriptor = assetManager.openFd("model_unquant.tflite");
            FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
            FileChannel fileChannel = inputStream.getChannel();
            long startOffset = fileDescriptor.getStartOffset();
            long declaredLength = fileDescriptor.getDeclaredLength();
            return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
        } catch (IOException e) {
            Log.e(TAG, "Error loading model file: " + e.getMessage(), e);
            throw e;
        }
    }

    private void recognizeImage(Bitmap bitmap) {
        try {
            // Resize the bitmap to the required input size for the model
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, 224, 224, true);

            // Convert bitmap to ByteBuffer
            ByteBuffer inputBuffer = ByteBuffer.allocateDirect(4 * 224 * 224 * 3);
            inputBuffer.order(ByteOrder.nativeOrder());

            int[] intValues = new int[224 * 224];
            resizedBitmap.getPixels(intValues, 0, resizedBitmap.getWidth(), 0, 0,
                    resizedBitmap.getWidth(), resizedBitmap.getHeight());

            inputBuffer.rewind();

            // Normalize pixel values to [-1,1]
            for (int value : intValues) {
                inputBuffer.putFloat(((value >> 16) & 0xFF) / 255.0f * 2 - 1);
                inputBuffer.putFloat(((value >> 8) & 0xFF) / 255.0f * 2 - 1);
                inputBuffer.putFloat((value & 0xFF) / 255.0f * 2 - 1);
            }

            // Run inference
            float[][] outputBuffer = new float[1][2]; // Assuming 2 classes: dog and cat
            tflite.run(inputBuffer, outputBuffer);

            // Get results
            float dogScore = outputBuffer[0][0];
            float catScore = outputBuffer[0][1];

            Log.d(TAG, "Recognition results - Dog: " + dogScore + ", Cat: " + catScore);

            String result;
            if (dogScore > catScore) {
                result = "Đây là hình con chó (Độ tin cậy: " + String.format("%.1f", dogScore * 100) + "%)";
            } else {
                result = "Đây là hình con mèo (Độ tin cậy: " + String.format("%.1f", catScore * 100) + "%)";
            }

            resultText.setText(result);
            resultContainer.setVisibility(View.VISIBLE);

        } catch (Exception e) {
            Log.e(TAG, "Error recognizing image: " + e.getMessage(), e);
            Toast.makeText(this, "Lỗi khi nhận dạng hình ảnh: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cameraProvider != null) {
            cameraProvider.unbindAll();
        }
        if (tflite != null) {
            tflite.close();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (cameraProvider != null) {
            cameraProvider.unbindAll();
        }
    }
}

