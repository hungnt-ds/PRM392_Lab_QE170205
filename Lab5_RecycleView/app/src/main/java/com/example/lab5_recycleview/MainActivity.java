package com.example.lab5_recycleview;

import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FoodAdapter adapter;
    private List<Food> foodList;
    private Button btnAddFood;
    private static final int REQUEST_CODE_EDIT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        btnAddFood = findViewById(R.id.btnAddFood);

        foodList = new ArrayList<>();
        foodList.add(new Food("Pizza", 150000, "", "Pizza hải sản"));
        foodList.add(new Food("Burger", 50000, "", "Burger bò Mỹ"));

        adapter = new FoodAdapter(foodList, this, new FoodAdapter.OnFoodActionListener() {
            @Override
            public void onEdit(int position) {
                Intent intent = new Intent(MainActivity.this, EditFoodActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("name", foodList.get(position).getName());
                intent.putExtra("price", foodList.get(position).getPrice());
                intent.putExtra("description", foodList.get(position).getDescription());
                startActivityForResult(intent, REQUEST_CODE_EDIT);
            }

            @Override
            public void onDelete(int position) {
                foodList.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnAddFood.setOnClickListener(v -> {
//            foodList.add(new Food("Món mới", 100000, R.drawable.food_default, "Mô tả món mới"));
            foodList.add(new Food("Món mới", 100000, "", "Mô tả món mới"));

            adapter.notifyItemInserted(foodList.size() - 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_EDIT && resultCode == RESULT_OK && data != null) {
            int position = data.getIntExtra("position", -1);
            if (position != -1) {
                foodList.get(position).setName(data.getStringExtra("name"));
                foodList.get(position).setPrice(data.getIntExtra("price", 0));
                foodList.get(position).setDescription(data.getStringExtra("description"));
                adapter.notifyItemChanged(position);
                if (data.getStringExtra("imageUri") != null) {
                    foodList.get(position).setImageUri((data.getStringExtra("imageUri")));
                }
            }
        }
    }
}
