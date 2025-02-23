package com.example.lab5_recycleview;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private List<Food> foodList;
    private Context context;
    private OnFoodActionListener listener;

    public interface OnFoodActionListener {
        void onEdit(int position);
        void onDelete(int position);
    }

    public FoodAdapter(List<Food> foodList, Context context, OnFoodActionListener listener) {
        this.foodList = foodList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food = foodList.get(position);
        holder.tvName.setText(food.getName());
        holder.tvPrice.setText(food.getPrice() + " VND");
        holder.tvDescription.setText(food.getDescription());

        if (food.getImageUri() != null && !food.getImageUri().isEmpty()) {
            Uri imageUri = Uri.parse(food.getImageUri());
            holder.imgFood.setImageURI(imageUri);
        } else {
            holder.imgFood.setImageResource(R.drawable.food_default);
        }

        holder.itemView.setOnLongClickListener(v -> {
            showDeleteDialog(position);
            return true;
        });

        holder.itemView.setOnClickListener(v -> listener.onEdit(position));
    }


    @Override
    public int getItemCount() {
        return foodList.size();
    }

    private void showDeleteDialog(int position) {
        new AlertDialog.Builder(context)
                .setTitle("Xóa món ăn")
                .setMessage("Bạn có chắc chắn muốn xóa món này?")
                .setPositiveButton("Xóa", (dialog, which) -> listener.onDelete(position))
                .setNegativeButton("Hủy", null)
                .show();
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice, tvDescription;
        ImageView imgFood;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            imgFood = itemView.findViewById(R.id.imgFood);
        }
    }
}
