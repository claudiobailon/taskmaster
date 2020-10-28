package com.claudiobailon.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTask extends AppCompatActivity {

    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);


        Button addButton = AddTask.this.findViewById(R.id.addTaskNow);
        addButton.setOnClickListener((view) -> {

            Toast.makeText(getApplicationContext(), "Task Added!", Toast.LENGTH_SHORT).show();

            db = Room.databaseBuilder(getApplicationContext(),Database.class, "claudiobailon_task_master")
                    .allowMainThreadQueries()
                    .build();

            EditText title = findViewById(R.id.taskTitle);
            EditText body = findViewById(R.id.taskDescription);

            Task task = new Task(title.getText().toString(),body.getText().toString(),"new");
            db.taskDAO().saveTask(task);

        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(AddTask.this, MainActivity.class);
        AddTask.this.startActivity(intent);
        return true;
    }
}