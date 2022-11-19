package com.example.firstproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.firstproject.databinding.ActivityMainBinding;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter adapter;
    private MainViewModel mViewModel;
    private static final int EDIT_TODO_ITEM_ACTIVITY = 0;

    public void addItem (View v) {
        Intent i = new Intent (this, EditToDoItemActivity.class);
        startActivityForResult (i, EDIT_TODO_ITEM_ACTIVITY);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot ();
        setContentView(view);

        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        //listenerSetup();
        observerSetup();
        recyclerSetup();
    }

    private void observerSetup() {

        mViewModel.getAllTodoItems().observe(this,
                new Observer<List<TodoItem>>() {
                    @Override
                    public void onChanged(@Nullable final List<TodoItem> todoItems) {
                        adapter.setTodoItemList(todoItems);
                    }
                });

        mViewModel.getDisplayedTodoItems().observe(this,
                new Observer<List<TodoItem>>() {
                    @Override
                    public void onChanged(@Nullable final List<TodoItem> products) {
                        // TODO
                    }
                });
    }

    private void recyclerSetup() {
        layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter();
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_TODO_ITEM_ACTIVITY) {
            if(resultCode == Activity.RESULT_OK){
                String title = data.getStringExtra(EditToDoItemActivity.TITLE);
                String desc = data.getStringExtra(EditToDoItemActivity.DESCRIPTION);
                String priority = data.getStringExtra(EditToDoItemActivity.PRIORITY);
                String period = data.getStringExtra(EditToDoItemActivity.PERIOD);

                TodoItem newItem = new TodoItem(title, desc, priority, period);
                mViewModel.insertTodoItem(newItem);
            }
        }
    } //onActivityResult
}