package com.example.lab4_lifecycle;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BundleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bundle);

        TextView textView = findViewById(R.id.txtBundleData);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String user = bundle.getString("user", "Không có dữ liệu");
            int age = bundle.getInt("age", 0);
            textView.setText("User: " + user + "\nTuổi: " + age);
        }

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

    }
}
