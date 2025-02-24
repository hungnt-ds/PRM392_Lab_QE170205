package com.example.lab4_lifecycle;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivityLifecycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.d(TAG, "SECOND ACTIVITY - onCreate: SecondActivity được tạo");

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "SECOND ACTIVITY - onStart: SecondActivity hiển thị với người dùng");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "SECOND ACTIVITY - onResume: SecondActivity bắt đầu tương tác với người dùng");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "SECOND ACTIVITY - onPause: SecondActivity bị che phủ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "SECOND ACTIVITY - onStop: SecondActivity không còn hiển thị");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "SECOND ACTIVITY - onDestroy: SecondActivity bị hủy");
    }
}