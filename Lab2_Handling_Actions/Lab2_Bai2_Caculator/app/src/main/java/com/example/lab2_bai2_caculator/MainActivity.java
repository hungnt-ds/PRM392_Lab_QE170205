package com.example.lab2_bai2_caculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText soThuNhatTxt, soThuHaiTxt;
    private TextView resultTxt;
    private Button congBtn, truBtn, nhanBtn, chiaBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        soThuNhatTxt = findViewById(R.id.soThuNhatTxt);
        soThuHaiTxt = findViewById(R.id.soThuHaiTxt);
        resultTxt = findViewById(R.id.resultTxt);

        congBtn = findViewById(R.id.congBtn);
        truBtn = findViewById(R.id.truBtn);
        nhanBtn = findViewById(R.id.nhanBtn);
        chiaBtn = findViewById(R.id.chiaBtn);

        try {
            congBtn.setOnClickListener(v -> calculate("+"));
            truBtn.setOnClickListener(v -> calculate("-"));
            nhanBtn.setOnClickListener(v -> calculate("*"));
            chiaBtn.setOnClickListener(v -> calculate("/"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void calculate(String operator) {
        // Lấy dữ liệu từ EditText
        String soThuNhatStr = soThuNhatTxt.getText().toString();
        String soThuHaiStr = soThuHaiTxt.getText().toString();

        if (soThuNhatStr.isEmpty() || soThuHaiStr.isEmpty()) {
            resultTxt.setText("Vui lòng nhập đủ 2 số.");
            return;
        }

        double soThuNhat = Double.parseDouble(soThuNhatStr);
        double soThuHai = Double.parseDouble(soThuHaiStr);
        double result = 0;

        // Tính toán dựa trên phép toán
        switch (operator) {
            case "+":
                result = soThuNhat + soThuHai;
                break;
            case "-":
                result = soThuNhat - soThuHai;
                break;
            case "*":
                result = soThuNhat * soThuHai;
                break;
            case "/":
                if (soThuHai == 0) {
                    resultTxt.setText("Không thể chia cho 0!");
                    return;
                }
                result = soThuNhat / soThuHai;
                break;
        }

        // Hiển thị kết quả
        resultTxt.setText("" + result);
    }
}