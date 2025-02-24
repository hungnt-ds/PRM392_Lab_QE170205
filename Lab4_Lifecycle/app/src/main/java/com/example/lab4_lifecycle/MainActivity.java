package com.example.lab4_lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ActivityLifecycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "MAIN ACTIVITY - onCreate: Activity được tạo");

        Button btnOpenSecondActivity = findViewById(R.id.btnOpenSecondActivity);
        btnOpenSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        Button btnString = findViewById(R.id.btnString);
        Button btnNumber = findViewById(R.id.btnNumber);
        Button btnArray = findViewById(R.id.btnArray);
        Button btnObject = findViewById(R.id.btnObject);
        Button btnBundle = findViewById(R.id.btnBundle);

        btnString.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, StringActivity.class);
            intent.putExtra("message", "Hello from MainActivity");
            startActivity(intent);
        });

        btnNumber.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NumberActivity.class);
            intent.putExtra("number", 2024);
            startActivity(intent);
        });

        btnArray.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ArrayActivity.class);
            String[] names = {"Alice", "Bob", "Charlie"};
            intent.putExtra("namesArray", names);
            startActivity(intent);
        });

        btnObject.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ObjectActivity.class);
            Person person = new Person("John Doe", 25);
            intent.putExtra("person", person);
            startActivity(intent);
        });

        btnBundle.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BundleActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("user", "Hung Nguyen Tan");
            bundle.putInt("age", 21);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "MAIN ACTIVITY - onStart: Activity hiển thị với người dùng");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "MAIN ACTIVITY - onResume: Activity bắt đầu tương tác với người dùng");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "MAIN ACTIVITY - onPause: Activity bị che phủ (chưa bị hủy)");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "MAIN ACTIVITY - onStop: Activity không còn hiển thị với người dùng");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "MAIN ACTIVITY - onRestart: Activity sắp hiển thị lại sau khi bị dừng");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MAIN ACTIVITY - onDestroy: Activity bị hủy");
    }
}