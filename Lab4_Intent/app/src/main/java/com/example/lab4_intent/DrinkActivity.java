package com.example.lab4_intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DrinkActivity extends AppCompatActivity {
    private RecyclerView recyclerViewDrink;
    private DrinkAdapter drinkAdapter;
    private List<Drink> drinkList;
    private List<Drink> selectedDrinks;
    private Button btnConfirmDrink;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        recyclerViewDrink = findViewById(R.id.recyclerViewDrink);
        btnConfirmDrink = findViewById(R.id.btnConfirmDrink);

        drinkList = new ArrayList<>();
        selectedDrinks = new ArrayList<>();

        drinkList.add(new Drink("Nuoc Suoi", 120000, R.drawable.water, "Nuoc Suoi"));
        drinkList.add(new Drink("7 Up", 50000, R.drawable.seven_up, "7 Up"));
        drinkList.add(new Drink("CocaCola", 150000, R.drawable.cocacola, "CocaCola dac biet"));

        drinkAdapter = new DrinkAdapter(drinkList, selectedDrinks);
        recyclerViewDrink.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewDrink.setAdapter(drinkAdapter);

        btnConfirmDrink.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("selectedDrinks", (ArrayList<Drink>) selectedDrinks);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}