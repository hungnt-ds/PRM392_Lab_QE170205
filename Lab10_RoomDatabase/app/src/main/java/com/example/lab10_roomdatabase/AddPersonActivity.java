package com.example.lab10_roomdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.lab10_roomdatabase.adapter.AppDatabase;
import com.example.lab10_roomdatabase.constants.Constants;
import com.example.lab10_roomdatabase.model.Person;

public class AddPersonActivity extends AppCompatActivity {
    private EditText etFirstName;
    private EditText etLastName;
    private Button btnSave;
    private int mPersonId;
    private Intent intent;
    private AppDatabase mDb;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initViews();

        // Khởi tạo Room Database
        mDb = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "app-database")
                .fallbackToDestructiveMigration()
                .build();

        // Kiểm tra Intent có dữ liệu hay không
        intent = getIntent();
        if (intent != null && intent.hasExtra(Constants.UPDATE_Person_Id)) {
            btnSave.setText("Update");
            mPersonId = intent.getIntExtra(Constants.UPDATE_Person_Id, -1);
            loadPersonFromDatabase(mPersonId);
        }
    }

    private void loadPersonFromDatabase(final int personId) {
        if (personId == -1) return;

        // Kiểm tra AppExecutors
        if (AppExecutors.getInstance() == null) {
            Log.e("AppExecutors", "AppExecutors instance is null!");
            return;
        }

        if (AppExecutors.getInstance().diskIO() == null) {
            Log.e("AppExecutors", "AppExecutors instance is null!");
            return;
        }

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                Log.d("Database", "Bắt đầu thực hiện truy vấn database");

                try {
                    final Person person = mDb.personDao().loadPersonById(personId);
                    Log.d("Database", "Truy vấn thành công: " + (person != null ? person.getFirstName() : "Không tìm thấy"));

                    if (person == null) {
                        Log.e("Database", "Person not found in database!");
                        return;
                    }

                    AppExecutors.getInstance().mainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("Database", "Chuyển dữ liệu lên UI");
                            populateUI(person);
                        }
                    });
                } catch (Exception e) {
                    Log.e("Database", "Lỗi khi truy vấn database", e);
                }
            }
        });

    }

    private void populateUI(Person person) {
        if (person == null) return;

        etFirstName.setText(person.getFirstName());
        etLastName.setText(person.getLastName());
    }

    private void initViews() {
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveButtonClicked();
            }
        });
    }

    public void onSaveButtonClicked() {
        final Person person = new Person(
                etFirstName.getText().toString(),
                etLastName.getText().toString()
        );

        if (AppExecutors.getInstance() == null) {
            Log.e("AppExecutors", "AppExecutors instance is null!");
            return;
        }

        if (AppExecutors.getInstance().diskIO() == null) {
            Log.e("AppExecutors", "AppExecutors instance is null!");
            return;
        }

        try {
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    Log.d("AppExecutors", "Disk IO started");
                    try {
                        if (!intent.hasExtra(Constants.UPDATE_Person_Id)) {
                            mDb.personDao().insert(person);
                        } else {
                            person.setUid(mPersonId);
                            mDb.personDao().update(person);
                        }
                        Log.d("AppExecutors", "Database operation completed");

                        AppExecutors.getInstance().mainThread().execute(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("AppExecutors", "Main thread execution started");
                                Intent resultIntent = new Intent();
                                setResult(RESULT_OK, resultIntent);
                                finish();
                                Log.d("AppExecutors", "Activity finished");
                            }
                        });
                    } catch (Exception e) {
                        Log.e("AppExecutors", "Error in diskIO thread", e);
                    }
                }
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}