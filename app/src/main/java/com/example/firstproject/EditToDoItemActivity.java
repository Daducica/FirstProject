package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.firstproject.databinding.EditTodoitemBinding;

public class EditToDoItemActivity extends AppCompatActivity {

    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String PRIORITY = "priority";
    public static final String PERIOD = "period";

    private EditTodoitemBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = EditTodoitemBinding.inflate(getLayoutInflater());
        View view = binding.getRoot ();
        setContentView(view);
    }

    public void onSaveClicked (View v) {
        Intent returnIntent = new Intent();

        returnIntent.putExtra(TITLE, binding.titleEditText.getText().toString());
        returnIntent.putExtra(DESCRIPTION, binding.descriptionEditText.getText().toString());
        returnIntent.putExtra(PRIORITY, binding.priorityEditText.getText().toString());
        returnIntent.putExtra(PERIOD, binding.periodEditText.getText().toString());

        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }


    public void onCancelClicked (View v) {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }
}