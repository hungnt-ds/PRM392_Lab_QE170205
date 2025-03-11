package com.example.lab15_onlinepayment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OrderPayment extends AppCompatActivity {
    Button btnConfirm;
    EditText edtSoluong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnConfirm = findViewById(R.id.buttonConfirm);
        edtSoluong = findViewById(R.id.editTextSoLuong);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if the EditText is empty
                if (edtSoluong.getText() == null || edtSoluong.getText().toString().isEmpty()) {
                    Toast.makeText(OrderPayment.this, "Nhập số lượng muốn mua", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        String s = edtSoluong.getText().toString();
                        double quantity = Double.parseDouble(s);
                        double total = quantity * 1000000;

                        // Pass the data to the next activity
                        Intent intent = new Intent(OrderPayment.this, PrintOrder.class);
                        intent.putExtra("soluong", s);
                        intent.putExtra("total", total);
                        startActivity(intent);
                    } catch (NumberFormatException e) {
                        Toast.makeText(OrderPayment.this, "Số lượng không hợp lệ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}