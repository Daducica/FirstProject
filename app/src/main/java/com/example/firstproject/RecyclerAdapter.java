package com.example.firstproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
{
    private List<TodoItem> todoItemList;

    public void setTodoItemList(List<TodoItem> todoItems) {
        todoItemList = todoItems;
        notifyDataSetChanged();
    }

    // temporary
    /* public void addItem (TodoItem newItem) {
        todoItemList.add(newItem);
    }*/

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from (parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TodoItem todoItem = todoItemList.get(position);
        holder.itemTitle.setText (todoItem.getTitle());
        holder.itemDescription.setText (todoItem.getDescription());
        holder.period.setText (todoItem.getPeriod().toString());
        holder.priority.setText (todoItem.getPriority().toString());
    }

    @Override
    public int getItemCount() {
        return todoItemList == null ? 0 : todoItemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        TextView itemDescription;
        TextView period;
        TextView priority;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById (R.id.itemTitle);
            itemDescription = itemView.findViewById (R.id.itemDescription);
            period = itemView.findViewById (R.id.period);
            priority = itemView.findViewById (R.id.priority);
        }
    }
}
