package com.example.lab11_api;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab11_api.model.Trainee;

import java.util.List;

public class TraineeAdapter extends RecyclerView.Adapter<TraineeAdapter.TraineeViewHolder> {

    private List<Trainee> traineeList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Trainee trainee);
    }

    public TraineeAdapter(List<Trainee> traineeList, OnItemClickListener listener) {
        this.traineeList = traineeList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TraineeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trainee, parent, false);
        return new TraineeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TraineeViewHolder holder, int position) {
        Trainee trainee = traineeList.get(position);
        holder.tvName.setText(trainee.getName());
        holder.tvEmail.setText(trainee.getEmail());

        holder.itemView.setOnClickListener(v -> listener.onItemClick(trainee));
    }

    @Override
    public int getItemCount() {
        return traineeList.size();
    }

    static class TraineeViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvEmail;

        public TraineeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }
    }
}
