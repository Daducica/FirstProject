package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

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

        ArrayAdapter<CharSequence> periodAdapter = ArrayAdapter.createFromResource(this,
                R.array.period_array, android.R.layout.simple_spinner_item);
        periodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.periodSpinner.setAdapter(periodAdapter);

        ArrayAdapter<CharSequence> priorityAdapter = ArrayAdapter.createFromResource(this,
                R.array.priority_array, android.R.layout.simple_spinner_item);
        priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.prioritySpinner.setAdapter(priorityAdapter);

        Intent intent = getIntent();
        String title = intent.getStringExtra(TITLE);
        if (title != null) {
            binding.titleEditText.setText (title);
            binding.descriptionEditText.setText (intent.getStringExtra(DESCRIPTION));
            //TODO
            //binding.prioritySpinner.setSele intent.getStringExtra(EditToDoItemActivity.PRIORITY);
            //String period = intent.getStringExtra(EditToDoItemActivity.PERIOD);
        }
    }

    public void onSaveClicked (View v) {

        if (binding.titleEditText.getText ().toString().isEmpty()) {
            Toast.makeText (getApplicationContext(), getString(R.string.title_empty_warning), Toast.LENGTH_SHORT).show ();
            return;
        }

        Intent returnIntent = new Intent();

        returnIntent.putExtra(TITLE, binding.titleEditText.getText().toString());
        returnIntent.putExtra(DESCRIPTION, binding.descriptionEditText.getText().toString());
        returnIntent.putExtra(PRIORITY, binding.prioritySpinner.getSelectedItem().toString());
        returnIntent.putExtra(PERIOD, binding.periodSpinner.getSelectedItem().toString());

        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }


    public void onCancelClicked (View v) {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

}