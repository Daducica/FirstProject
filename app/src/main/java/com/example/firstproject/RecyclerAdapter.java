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
        holder.itemTitle.setText (todoItemList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return todoItemList == null ? 0 : todoItemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById (R.id.itemTitle);
        }
    }
}
