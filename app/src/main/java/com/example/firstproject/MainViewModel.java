package com.example.firstproject;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private TodoItemRepository repository;
    private LiveData<List<TodoItem>> allTodoItems;
    private MutableLiveData<List<TodoItem>> displayedTodoItems;

    public MainViewModel (Application application) {
        super(application);
        repository = new TodoItemRepository(application);
        allTodoItems = repository.getAllProducts();
        displayedTodoItems = repository.getDisplayedItems();
    }

    MutableLiveData<List<TodoItem>> getDisplayedTodoItems() {
        return displayedTodoItems;
    }

    LiveData<List<TodoItem>> getAllTodoItems() {
        return allTodoItems;
    }

    public void insertTodoItem(TodoItem todoItem) {
        repository.insertTodoItem(todoItem);
    }

    public void findTodoItem(String title) {
        repository.findTodoItem(title);
    }

}
