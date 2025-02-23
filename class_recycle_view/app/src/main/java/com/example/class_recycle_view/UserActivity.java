package com.example.class_recycle_view;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {

    ArrayList<User> userlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        //Lookup the RecycleView in Activity layout
        RecyclerView rvUser = findViewById(R.id.rvUser);
        // initialize user list
        userlist = new ArrayList<>();
        userlist.add(new User("NguyenTT", "Tran Thanh Nguyen",
        "Nguyentt@ftp.edu.vn"));
        userlist.add(new User("Antv", "Tran Van An", "antv@gmail.com"));
        userlist.add(new User("BangTT", "Tran Thanh Bang",
        "bangtt@gmail.com"));
        userlist.add(new User("KhangTT", "Tran Thanh Khang",
        "khangtt@gmail.com"));
        userlist.add(new User("BaoNT", "Nguyen Thanh Bao", "baott@gmail.com"))
        ;
        userlist.add(new User("HungVH", "Võ Huy Hung", "hungvh@gmail.com"));
        //Create adapter passing in the sample user data
        UserAdapter adapter = new UserAdapter(userlist);
        //Attach the Adapter to the Recycleview to populist item
        rvUser.setAdapter(adapter);
        //set layout manager to position the item
        rvUser.setLayoutManager(new LinearLayoutManager(this));
    }
}