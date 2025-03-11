package com.example.lab10_roomdatabase;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.lab10_roomdatabase.adapter.AppDatabase;
import com.example.lab10_roomdatabase.adapter.PersonAdapter;
import com.example.lab10_roomdatabase.model.Person;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_PERSON_REQUEST = 1;
    private RecyclerView recyclerView;
    private PersonAdapter adapter;
    private List<Person> personList;
    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);

        personList = new ArrayList<>();
//        adapter = new PersonAdapter(personList);
        adapter = new PersonAdapter(this);
        adapter.setTasks(personList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        mDb = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "app-database").build();

        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddPersonActivity.class);
            startActivityForResult(intent, ADD_PERSON_REQUEST);
        });
        loadPersons();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPersons(); // Gọi lại hàm lấy dữ liệu từ database
    }

    private void loadPersons() {
        AppExecutors.getInstance().diskIO().execute(() -> {
            List<Person> persons = mDb.personDao().getAll();
            runOnUiThread(() -> {
                adapter.setTasks(persons);
                adapter.notifyDataSetChanged(); // Đảm bảo RecyclerView cập nhật
            });
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == ADD_PERSON_REQUEST && resultCode == RESULT_OK && data != null) {
//            String firstName = data.getStringExtra("FIRST_NAME");
//            String lastName = data.getStringExtra("LAST_NAME");
//            personList.add(new Person(firstName, lastName));
//            adapter.notifyDataSetChanged();
//        }
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            loadPersons(); // Tải lại danh sách sau khi thêm hoặc chỉnh sửa
        }
    }
}