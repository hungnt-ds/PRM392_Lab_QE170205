package com.example.lap2_bai1_randomnumber;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView txtBinding;
    TextView maxTxt;
    TextView minTxt;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Anh xa
        txtBinding = (TextView) findViewById(R.id.resultTxt);
        maxTxt = (TextView) findViewById(R.id.maxTxt);
        minTxt = (TextView) findViewById(R.id.minTxt);

        int max = Integer.parseInt(maxTxt.getText().toString());
        int min = Integer.parseInt(minTxt.getText().toString());

        button = (Button) findViewById(R.id.myBtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy giá trị từ EditText
                String maxText = maxTxt.getText().toString();
                String minText = minTxt.getText().toString();

                if (maxText.isEmpty() || minText.isEmpty()) {
                    txtBinding.setText("Vui lòng nhập Min và Max!");
                    return;
                }

                int max = Integer.parseInt(maxText);
                int min = Integer.parseInt(minText);

                if (max <= min) {
                    txtBinding.setText("Max phải lớn hơn Min!");
                    return;
                }

                // Tạo số ngẫu nhiên
                Random random = new Random();
                int randomValue = random.nextInt((max - min) + 1) + min;

                // Lấy thời gian hiện tại
                String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss 'UTC'", Locale.getDefault()).format(new Date());

                // Hiển thị kết quả
                String result = randomValue + "\nMin: " + min + ", Max: " + max + "\n" + currentTime;
                txtBinding.setText(result);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}