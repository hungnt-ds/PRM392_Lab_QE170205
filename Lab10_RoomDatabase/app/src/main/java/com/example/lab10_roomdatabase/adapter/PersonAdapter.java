package com.example.lab10_roomdatabase.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab10_roomdatabase.AddPersonActivity;
import com.example.lab10_roomdatabase.AppExecutors;
import com.example.lab10_roomdatabase.R;
import com.example.lab10_roomdatabase.constants.Constants;
import com.example.lab10_roomdatabase.model.Person;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {
    private Context context;
    private List<Person> mPersonList;
    private AppDatabase mDb;

    public PersonAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.person_item,
                viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.name.setText(mPersonList.get(i).getFirstName());
        myViewHolder.email.setText(mPersonList.get(i).getLastName());
    }

    @Override
    public int getItemCount() {
        if (mPersonList == null) {
            return 0;
        }
        return mPersonList.size();
    }

    public void setTasks(List<Person> personList) {
        mPersonList = personList;
        notifyDataSetChanged();
    }

    public List<Person> getTasks() {
        return mPersonList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, email;
        ImageView editImage, deleteImage;
        MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvFirstName);
            email = itemView.findViewById(R.id.tvLastName);
            editImage = itemView.findViewById(R.id.ivEdit);
            deleteImage = itemView.findViewById(R.id.ivDelete);
            editImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && mPersonList != null) {
                        int elementId = mPersonList.get(position).getUid();
                        Intent intent = new Intent(context, AddPersonActivity.class);
                        intent.putExtra(Constants.UPDATE_Person_Id, elementId);
                        context.startActivity(intent);
                    }
                }
            });

            deleteImage.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    deletePerson(mPersonList.get(position)); // Gọi hàm xóa
                }
            });
        }

        private void deletePerson(Person person) {
            AppExecutors.getInstance().diskIO().execute(() -> {
                Log.d("DatabaseCheck", "Xóa person: " + person.getUid());
                AppDatabase.getInstance(context).personDao().delete(person);

                // Kiểm tra lại database sau khi xóa
                List<Person> allPersons = AppDatabase.getInstance(context).personDao().getAll();
                Log.d("DatabaseCheck", "Số lượng sau khi xóa: " + allPersons.size());

                AppExecutors.getInstance().mainThread().execute(() -> {
                    mPersonList.remove(person);
                    notifyDataSetChanged();
                });
            });
        }
    }
}
