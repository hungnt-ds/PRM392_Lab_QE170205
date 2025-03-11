package com.example.lab10_roomdatabase.adapter;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.lab10_roomdatabase.DAO.PersonDao;
import com.example.lab10_roomdatabase.model.Person;

@Database(entities = {Person.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public abstract PersonDao personDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "app-database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
