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

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private List<Food> foodList;
    private List<Food> selectedFoods;

    public FoodAdapter(List<Food> foodList, List<Food> selectedFoods) {
        this.foodList = foodList;
        this.selectedFoods = selectedFoods;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food = foodList.get(position);
        holder.imgFood.setImageResource(food.getImageResource());
        holder.txtFoodName.setText(food.getName());
        holder.txtFoodPrice.setText("Giá: " + food.getPrice() + "đ");
        holder.txtFoodDescription.setText(food.getDescription());

        holder.chkFood.setOnCheckedChangeListener(null);
        holder.chkFood.setChecked(selectedFoods.contains(food));

        holder.chkFood.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedFoods.add(food);
            } else {
                selectedFoods.remove(food);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFood;
        TextView txtFoodName, txtFoodPrice, txtFoodDescription;
        CheckBox chkFood;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.imgFood);
            txtFoodName = itemView.findViewById(R.id.txtFoodName);
            txtFoodPrice = itemView.findViewById(R.id.txtFoodPrice);
            txtFoodDescription = itemView.findViewById(R.id.txtFoodDescription);
            chkFood = itemView.findViewById(R.id.chkFood);
        }
    }
}
