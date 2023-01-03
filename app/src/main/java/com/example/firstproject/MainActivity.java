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
import com.google.android.material.tabs.TabLayout;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements ViewHolderDelegate {

    private ActivityMainBinding binding;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter adapter;
    private MainViewModel mViewModel;
    private static final int EDIT_TODO_ITEM_ACTIVITY = 0;
    private static final int ADD_TODO_ITEM_ACTIVITY = 1;

    public void addItem (View v) {
        Intent i = new Intent (this, EditToDoItemActivity.class);
        startActivityForResult (i, ADD_TODO_ITEM_ACTIVITY);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot ();
        setContentView(view);

        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel.filterTodoItems(TodoItem.Period.Day);
        //listenerSetup();
        observerSetup();
        recyclerSetup();
        tabSetup ();
    }

    private void observerSetup() {

        mViewModel.getAllTodoItems().observe(this,
                new Observer<List<TodoItem>>() {
                    @Override
                    public void onChanged(@Nullable final List<TodoItem> todoItems) {
                        //adapter.setTodoItemList(todoItems);
                    }
                });

        mViewModel.getDisplayedTodoItems().observe(this,
                new Observer<List<TodoItem>>() {
                    @Override
                    public void onChanged(@Nullable final List<TodoItem> todoItems) {
                        adapter.setTodoItemList(todoItems);
                    }
                });
    }

    private void recyclerSetup() {
        layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter(this);
        binding.recyclerView.setAdapter(adapter);
    }

    void tabSetup () {
        TabLayout tabLayout = binding.tabLayout;
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                final int pos = tab.getPosition();
                if (pos == 0) {
                    mViewModel.filterTodoItems(TodoItem.Period.Day);
                } if (pos == 1) {
                    mViewModel.filterTodoItems(TodoItem.Period.Week);
                } else {
                    mViewModel.filterTodoItems(null);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){
            if (requestCode == ADD_TODO_ITEM_ACTIVITY) {
                String title = data.getStringExtra(EditToDoItemActivity.TITLE);
                String desc = data.getStringExtra(EditToDoItemActivity.DESCRIPTION);
                String priority = data.getStringExtra(EditToDoItemActivity.PRIORITY);
                String period = data.getStringExtra(EditToDoItemActivity.PERIOD);

                TodoItem newItem = new TodoItem(title, desc, priority, period);
                mViewModel.insertTodoItem(newItem);
            } else if (requestCode == EDIT_TODO_ITEM_ACTIVITY) {
                //TODO
            }
        }
    } //onActivityResult

    @Override
    public void OnClickAction(TodoItem item) {
        Intent i = new Intent (this, EditToDoItemActivity.class);

        i.putExtra(EditToDoItemActivity.TITLE, item.getTitle ());
        i.putExtra(EditToDoItemActivity.DESCRIPTION, item.getDescription());
        i.putExtra(EditToDoItemActivity.PRIORITY, item.getPriority ());
        i.putExtra(EditToDoItemActivity.PERIOD, item.getPeriod ());

        startActivityForResult (i, EDIT_TODO_ITEM_ACTIVITY);
    }
}