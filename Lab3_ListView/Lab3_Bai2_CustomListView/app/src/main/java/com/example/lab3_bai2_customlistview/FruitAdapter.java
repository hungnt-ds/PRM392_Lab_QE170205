package com.example.lab3_bai2_customlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class FruitAdapter extends ArrayAdapter<Fruit> {
    private Context context;
    private int resource;
    private List<Fruit> fruits;

    public FruitAdapter(Context context, int resource, List<Fruit> fruits) {
        super(context, resource, fruits);
        this.context = context;
        this.resource = resource;
        this.fruits = fruits;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvDescription = convertView.findViewById(R.id.tvDescription);

        Fruit fruit = fruits.get(position);
//        imageView.setImageResource(fruit.getImage());
        if (fruit.getImageUri() != null) {
            imageView.setImageURI(fruit.getImageUri());
        } else {
            imageView.setImageResource(fruit.getImageResource());
        }
        tvName.setText(fruit.getName());
        tvDescription.setText(fruit.getDescription());

        return convertView;
    }
}
