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
    private TodoItem.Period filter = TodoItem.Period.Day;

    public MainViewModel (Application application) {
        super(application);
        repository = new TodoItemRepository(application);
        allTodoItems = repository.getAllTodoItems();
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
        repository.filterItems(filter);
    }

    public void findTodoItem(String title) {
        repository.findTodoItem(title);
    }

    public void filterTodoItems(TodoItem.Period period) {
        repository.filterItems(period);
        filter = period;
    }

}
