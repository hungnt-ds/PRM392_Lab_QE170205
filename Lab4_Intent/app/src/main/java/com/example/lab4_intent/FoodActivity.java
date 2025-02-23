package com.example.lab4_intent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

//public class FoodActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_food);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//    }
//}

//public class FoodActivity extends AppCompatActivity {
//    private RadioGroup radioGroup;
//    private Button btnConfirm;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_food);
//
//        radioGroup = findViewById(R.id.radioGroupFood);
//        btnConfirm = findViewById(R.id.btnConfirmFood);
//
//        btnConfirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int selectedId = radioGroup.getCheckedRadioButtonId();
//                if (selectedId != -1) {
//                    RadioButton selectedFood = findViewById(selectedId);
//                    String foodChoice = selectedFood.getText().toString();
//
//                    Intent resultIntent = new Intent();
//                    resultIntent.putExtra("food", foodChoice);
//                    setResult(RESULT_OK, resultIntent);
//                    finish();
//                }
//            }
//        });
//    }
//}

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity {
    private RecyclerView recyclerViewFood;
    private FoodAdapter foodAdapter;
    private List<Food> foodList;
    private List<Food> selectedFoods;
    private Button btnConfirmFood;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        recyclerViewFood = findViewById(R.id.recyclerViewFood);
        btnConfirmFood = findViewById(R.id.btnConfirmFood);

        foodList = new ArrayList<>();
        selectedFoods = new ArrayList<>();

        foodList.add(new Food("Pizza", 120000, R.drawable.pizza, "Pizza thơm ngon"));
        foodList.add(new Food("Burger", 50000, R.drawable.burger, "Burger bò Mỹ"));
        foodList.add(new Food("Sushi", 150000, R.drawable.sushi, "Sushi Nhật Bản"));

        foodAdapter = new FoodAdapter(foodList, selectedFoods);
        recyclerViewFood.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewFood.setAdapter(foodAdapter);

        btnConfirmFood.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("selectedFoods", (ArrayList<Food>) selectedFoods);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
