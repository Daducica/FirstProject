package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.firstproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter adapter;
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

                int id = adapter.getItemCount();
                TodoItem newItem = new TodoItem(id, title, desc, priority, period);
                adapter.addItem (newItem);
                adapter.notifyItemInserted(adapter.getItemCount());
            }
        }
    } //onActivityResult
}