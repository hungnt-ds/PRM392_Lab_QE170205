package com.example.lab11_api;

import static android.app.ProgressDialog.show;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab11_api.api.TraineeRepository;
import com.example.lab11_api.api.TraineeService;
import com.example.lab11_api.model.Trainee;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TraineeService traineeService;
    EditText etId, etName, etEmail, etPhone, etGender;
    Button btnSave, btnViewAll, btnUpdate, btnDelete;
    RecyclerView recyclerView;
    List<Trainee> traineeList;
    TraineeAdapter traineeAdapter;
    private int selectedTraineeId = -1; // Mặc định -1 nếu chưa chọn ai


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ UI
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etGender = findViewById(R.id.etGender);

        btnSave = findViewById(R.id.btnSave);
        btnViewAll = findViewById(R.id.btnViewAll);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        recyclerView = findViewById(R.id.recyclerView);
        traineeList = new ArrayList<>();
        traineeAdapter = new TraineeAdapter(traineeList, this::fillDataToForm);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(traineeAdapter);

        // Gán sự kiện click
        btnSave.setOnClickListener(this);
        btnViewAll.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        traineeService = TraineeRepository.getTraineeService();
    }

    @Override
    public void onClick(View v) {
        if (v == btnSave) {
            save();
        } else if (v == btnViewAll) {
            getAllTrainees();
        } else if (v == btnUpdate) {
            updateTrainee();
        } else if (v == btnDelete) {
            deleteTrainee();
        }
    }

    private void fillDataToForm(Trainee trainee) {
        selectedTraineeId = (int) trainee.getId(); // Lưu id của trainee đang chọn
        etName.setText(trainee.getName());
        etEmail.setText(trainee.getEmail());
        etPhone.setText(trainee.getPhone());
        etGender.setText(trainee.getGender());
    }


    private void save() {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String phone = etPhone.getText().toString();
        String gender = etGender.getText().toString();

        Trainee trainee = new Trainee(name, email, phone, gender);

        Call<Trainee> call = traineeService.createTrainees(trainee);
        call.enqueue(new Callback<Trainee>() {
            @Override
            public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                Toast.makeText(MainActivity.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Trainee> call, Throwable t) {
                Log.e("Lỗi", t.getMessage());
                Toast.makeText(MainActivity.this, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getAllTrainees() {
        Call<Trainee[]> call = traineeService.getAllTrainees();
        call.enqueue(new Callback<Trainee[]>() {
            @Override
            public void onResponse(Call<Trainee[]> call, Response<Trainee[]> response) {
                if (response.body() != null) {
                    traineeList.clear();
                    for (Trainee t : response.body()) {
                        traineeList.add(t);
                    }
                    traineeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Trainee[]> call, Throwable t) {
                Log.e("Lỗi", t.getMessage());
            }
        });
    }


    private void updateTrainee() {
        if (selectedTraineeId == -1) {
            Toast.makeText(this, "Vui lòng chọn trainee cần cập nhật!", Toast.LENGTH_SHORT).show();
            return;
        }

        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String phone = etPhone.getText().toString();
        String gender = etGender.getText().toString();

        Trainee updatedTrainee = new Trainee(selectedTraineeId, name, email, phone, gender);

        Call<Trainee> call = traineeService.updateTrainees(selectedTraineeId, updatedTrainee);
        call.enqueue(new Callback<Trainee>() {
            @Override
            public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                    getAllTrainees(); // Cập nhật lại danh sách
                }
            }

            @Override
            public void onFailure(Call<Trainee> call, Throwable t) {
                Log.e("Lỗi cập nhật", t.getMessage());
            }
        });
    }


    private void deleteTrainee() {
        if (selectedTraineeId == -1) {
            Toast.makeText(this, "Vui lòng chọn trainee cần cập nhật!", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<Trainee> call = traineeService.deleteTrainees(selectedTraineeId);
        call.enqueue(new Callback<Trainee>() {
            @Override
            public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                Toast.makeText(MainActivity.this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Trainee> call, Throwable t) {
                Log.e("Lỗi", t.getMessage());
                Toast.makeText(MainActivity.this, "Xóa thất bại!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}