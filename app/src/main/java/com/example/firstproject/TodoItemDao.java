package com.example.firstproject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TodoItemDao {
    @Insert
    void insertTodoItem(TodoItem product);

    @Query("SELECT * FROM todoItems WHERE titleColumn = :title")
    List<TodoItem> findTodoItem(String title);

    @Query("SELECT * FROM todoItems")
    LiveData<List<TodoItem>> getAllTodoItems();

    @Query("SELECT * FROM todoItems WHERE periodColumn = :period")
    List<TodoItem> applyPeriodFilter (TodoItem.Period period);

    @Query("SELECT * FROM todoItems")
    List<TodoItem> applyNoPeriodFilter ();

}
