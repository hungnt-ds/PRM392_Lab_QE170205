package com.example.lab15_onlinepayment;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PrintOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_order);

        TextView textView = findViewById(R.id.textViewPrint);
        int soLuong = getIntent().getIntExtra("soluong", 0);
        double total = getIntent().getDoubleExtra("total", 0);

        textView.setText("Số lượng: " + soLuong + "\nTổng tiền: " + total);
    }
}