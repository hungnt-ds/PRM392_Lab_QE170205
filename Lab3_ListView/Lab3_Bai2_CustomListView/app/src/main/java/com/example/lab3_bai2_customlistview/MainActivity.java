package com.example.lab3_bai2_customlistview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import android.app.AlertDialog;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private FruitAdapter adapter;
    private ArrayList<Fruit> fruitList;
    private Button btnAdd;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;
    private ImageView ivFruit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        btnAdd = findViewById(R.id.btnAdd);

        // Dữ liệu mẫu
        fruitList = new ArrayList<>();
        fruitList.add(new Fruit(R.drawable.banana, "Chuối tiêu", "Chuối tiêu Long An"));
        fruitList.add(new Fruit(R.drawable.dragon_fruit, "Thanh Long", "Thanh long ruột đỏ"));

        adapter = new FruitAdapter(this, R.layout.item_fruit, fruitList);
        listView.setAdapter(adapter);

        // Thêm mới
        btnAdd.setOnClickListener(v -> showDialog(null, -1));

        // Cập nhật
        listView.setOnItemClickListener((parent, view, position, id) -> showDialog(fruitList.get(position), position));

        // Xóa
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            confirmDelete(position);
            return true;
        });
    }

    private void showDialog(Fruit fruit, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(fruit == null ? "Thêm mới" : "Chỉnh sửa");

        View view = getLayoutInflater().inflate(R.layout.dialog_fruit, null);
        EditText etName = view.findViewById(R.id.etName);
        EditText etDescription = view.findViewById(R.id.etDescription);
        ivFruit = view.findViewById(R.id.ivFruit);
        Button btnChooseImage = view.findViewById(R.id.btnChooseImage);

        // Nếu đang chỉnh sửa, hiển thị dữ liệu cũ
        if (fruit != null) {
            etName.setText(fruit.getName());
            etDescription.setText(fruit.getDescription());
            if (fruit.getImageUri() != null) {
                ivFruit.setImageURI(fruit.getImageUri());
                imageUri = fruit.getImageUri();
            } else {
                ivFruit.setImageResource(fruit.getImageResource());
            }
        }

        // Chọn ảnh từ thư viện
        btnChooseImage.setOnClickListener(v -> openGallery());

        builder.setView(view);
        builder.setPositiveButton("Lưu", (dialog, which) -> {
            String name = etName.getText().toString();
            String desc = etDescription.getText().toString();

            if (fruit == null) {
                fruitList.add(new Fruit(imageUri, name, desc)); // Sử dụng imageUri
            } else {
                fruit.setName(name);
                fruit.setDescription(desc);
                fruitList.set(position, fruit);
            }

            adapter.notifyDataSetChanged();
        });

        builder.setNegativeButton("Hủy", null);
        builder.show();
    }



    private void confirmDelete(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận xóa");
        builder.setMessage("Bạn có chắc muốn xóa không?");
        builder.setPositiveButton("Xóa", (dialog, which) -> {
            fruitList.remove(position);
            adapter.notifyDataSetChanged();
        });
        builder.setNegativeButton("Hủy", null);
        builder.show();
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            ivFruit.setImageURI(imageUri);
        }
    }

}
