package com.example.lab5_recycleview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class EditFoodActivity extends AppCompatActivity {
    private EditText edtName, edtPrice, edtDescription;
    private ImageView imgFood;
    private Button btnSelectImage, btnSave, btnCancel;
    private Uri imageUri;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food);

        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);
        edtDescription = findViewById(R.id.edtDescription);
        imgFood = findViewById(R.id.imgFood);
        btnSelectImage = findViewById(R.id.btnSelectImage);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        position = intent.getIntExtra("position", -1);
        String name = intent.getStringExtra("name");
        int price = intent.getIntExtra("price", 0);
        String description = intent.getStringExtra("description");

        // Hiển thị dữ liệu lên EditText
        edtName.setText(name);
        edtPrice.setText(String.valueOf(price));
        edtDescription.setText(description);

        // Xử lý chọn ảnh từ thư viện
        btnSelectImage.setOnClickListener(v -> openGallery());

        // Xử lý khi nhấn nút Lưu
        btnSave.setOnClickListener(v -> {
            String newName = edtName.getText().toString().trim();
            String newPriceStr = edtPrice.getText().toString().trim();
            String newDescription = edtDescription.getText().toString().trim();

            if (newName.isEmpty() || newPriceStr.isEmpty() || newDescription.isEmpty()) {
                Toast.makeText(EditFoodActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            int newPrice = Integer.parseInt(newPriceStr);

            // Trả kết quả về MainActivity
            Intent resultIntent = new Intent();
            resultIntent.putExtra("position", position);
            resultIntent.putExtra("name", newName);
            resultIntent.putExtra("price", newPrice);
            resultIntent.putExtra("description", newDescription);
            if (imageUri != null) {
                resultIntent.putExtra("imageUri", imageUri.toString());
            }

            setResult(RESULT_OK, resultIntent);
            finish();
        });

        // Xử lý khi nhấn nút Hủy
        btnCancel.setOnClickListener(v -> finish());
    }

    // Khởi tạo ActivityResultLauncher để chọn ảnh
    private final ActivityResultLauncher<Intent> imagePickerLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    imageUri = result.getData().getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                        imgFood.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        imagePickerLauncher.launch(intent);
    }
}