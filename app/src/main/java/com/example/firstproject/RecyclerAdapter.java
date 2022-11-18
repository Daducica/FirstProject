package com.example.firstproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
{
    // test data
    public ArrayList<TodoItem> testListItems = new ArrayList<>();

    RecyclerAdapter () {
        super ();
        // test data
        for (int i = 0; i < 10; i++) {
            TodoItem.Priority priority = TodoItem.Priority.values()[i%3];
            TodoItem.Period period = TodoItem.Period.values()[i%2];
            testListItems.add (new TodoItem (i, "TodoItem" + i,"TodoItemDesc" + i,
                    priority, period));
        }

    }

    // temporary
    public void addItem (TodoItem newItem) {
        testListItems.add(newItem);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from (parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemTitle.setText (testListItems.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return testListItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById (R.id.itemTitle);
        }
    }
}
