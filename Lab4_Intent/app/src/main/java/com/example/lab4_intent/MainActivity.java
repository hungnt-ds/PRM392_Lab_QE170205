package com.example.lab4_intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_FOOD = 1;
    private static final int REQUEST_DRINK = 2;

    private Button btnThucAn, btnDoUong, btnThoat;
    private TextView txtKetQua;
    ArrayList<Food> selectedFoods = new ArrayList<Food>();
    ArrayList<Drink> selectedDrinks = new ArrayList<Drink>();

    private int totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnThucAn = findViewById(R.id.btnThucAn);
        btnDoUong = findViewById(R.id.btnDoUong);
        btnThoat = findViewById(R.id.btnThoat);
        txtKetQua = findViewById(R.id.txtKetQua);

        btnThucAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FoodActivity.class);
                startActivityForResult(intent, REQUEST_FOOD);
            }
        });

        btnDoUong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DrinkActivity.class);
                startActivityForResult(intent, REQUEST_DRINK);
            }
        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_FOOD && data != null) {
//                ArrayList<Food> selectedFoods = (ArrayList<Food>) data.getSerializableExtra("selectedFoods");
                selectedFoods = (ArrayList<Food>) data.getSerializableExtra("selectedFoods");

                int foodPrice = 0;
                StringBuilder result = new StringBuilder("Bạn đã chọn:\n");

                for (Food food : selectedFoods) {
                    result.append(food.getName()).append(" - ").append(food.getPrice()).append("đ\n");
                    foodPrice += food.getPrice();
                }

                result.append("Tổng tiền: ").append(foodPrice).append("đ");
                totalPrice += foodPrice;
                txtKetQua.setText(result.toString());
            } else if (requestCode == REQUEST_DRINK && data != null) {
//                selectedDrink = data.getStringExtra("drink");
                selectedDrinks = (ArrayList<Drink>) data.getSerializableExtra("selectedDrinks");

                int drinkPrice = 0;
                StringBuilder result = new StringBuilder("Bạn đã chọn:\n");

                for (Drink drink : selectedDrinks) {
                    result.append(drink.getName()).append(" - ").append(drink.getPrice()).append("đ\n");
                    drinkPrice += drink.getPrice();
                }

                result.append("Tổng tiền: ").append(drinkPrice).append("đ");
                totalPrice += drinkPrice;
                txtKetQua.setText(result.toString());
            }
            updateResult();
        }
    }

    private void updateResult() {
        StringBuilder resultText = new StringBuilder("Bạn đã chọn:\n");
        totalPrice = 0;

        if (!selectedFoods.isEmpty()) {
            resultText.append("Món ăn: ");
            for (Food food : selectedFoods) {
                resultText.append(food.getName()).append(" (").append(food.getPrice()).append("đ), ");
                totalPrice += food.getPrice();
            }
            // Xóa dấu phẩy cuối cùng
            resultText.setLength(resultText.length() - 2);
        }

        if (!selectedDrinks.isEmpty()) {
//            resultText.append(" | Đồ uống: " + selectedDrink);
            resultText.append("\nĐồ uống: ");
            for (Drink drink : selectedDrinks) {
                resultText.append(drink.getName()).append(" (").append(drink.getPrice()).append("đ), ");
                totalPrice += drink.getPrice();
            }
            // Xóa dấu phẩy cuối cùng
            resultText.setLength(resultText.length() - 2);
        }

        resultText.append("\n Tổng tiền: " + totalPrice);


        txtKetQua.setText(resultText);
    }
}