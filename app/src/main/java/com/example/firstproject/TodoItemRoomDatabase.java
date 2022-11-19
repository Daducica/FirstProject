package com.example.firstproject;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TodoItem.class}, version = 1)
public abstract class TodoItemRoomDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "todo_item_database";
    public abstract TodoItemDao productDao();
    private static TodoItemRoomDatabase INSTANCE;

    static TodoItemRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TodoItemRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(),
                                    TodoItemRoomDatabase.class,
                                    DATABASE_NAME).build();
                }
            }
        }
        return INSTANCE;
    }
}