package com.example.lab4_intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder> {

    private List<Drink> drinkList;
    private List<Drink> selectedDrinks;

    public DrinkAdapter(List<Drink> drinkList, List<Drink> selectedDrinks) {
        this.drinkList = drinkList;
        this.selectedDrinks = selectedDrinks;
    }

    @NonNull
    @Override
    public DrinkAdapter.DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drink, parent, false);
        return new DrinkAdapter.DrinkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkAdapter.DrinkViewHolder holder, int position) {
        Drink drink = drinkList.get(position);
        holder.imgDrink.setImageResource(drink.getImageResource());
        holder.txtDrinkName.setText(drink.getName());
        holder.txtDrinkPrice.setText("Giá: " + drink.getPrice() + "đ");
        holder.txtDrinkDescription.setText(drink.getDescription());

        holder.chkDrink.setOnCheckedChangeListener(null);
        holder.chkDrink.setChecked(selectedDrinks.contains(drink));

        holder.chkDrink.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedDrinks.add(drink);
            } else {
                selectedDrinks.remove(drink);
            }
        });
    }

    @Override
    public int getItemCount() {
        return drinkList.size();
    }

    public static class DrinkViewHolder extends RecyclerView.ViewHolder {
        ImageView imgDrink;
        TextView txtDrinkName, txtDrinkPrice, txtDrinkDescription;
        CheckBox chkDrink;

        public DrinkViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDrink = itemView.findViewById(R.id.imgDrink);
            txtDrinkName = itemView.findViewById(R.id.txtDrinkName);
            txtDrinkPrice = itemView.findViewById(R.id.txtDrinkPrice);
            txtDrinkDescription = itemView.findViewById(R.id.txtDrinkDescription);
            chkDrink = itemView.findViewById(R.id.chkDrink);
        }
    }
}