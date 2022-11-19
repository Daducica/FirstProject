package com.example.firstproject;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TodoItemRepository {
    private final MutableLiveData<List<TodoItem>> displayedItems =
            new MutableLiveData<>();
    private List<TodoItem> displayedItemList;
    private final LiveData<List<TodoItem>> allTodoItems;
    private final TodoItemDao todoItemDao;

    public TodoItemRepository(Application application) {
        TodoItemRoomDatabase db;
        db = TodoItemRoomDatabase.getDatabase(application);
        todoItemDao = db.productDao();
        allTodoItems = todoItemDao.getAllTodoItems();
    }

    public LiveData<List<TodoItem>> getAllProducts() {
        return allTodoItems;
    }

    public MutableLiveData<List<TodoItem>> getDisplayedItems() {
        return displayedItems;
    }

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override public void handleMessage(Message msg) {
            displayedItems.setValue(displayedItemList);
        }
    };

    public void insertTodoItem(TodoItem newItem) {

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            todoItemDao.insertTodoItem(newItem);

        });
        executor.shutdown();
    }

    public void findTodoItem(String name) {

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            displayedItemList = todoItemDao.findTodoItem(name);
            handler.sendEmptyMessage(0);
        });
        executor.shutdown();
    }
}
