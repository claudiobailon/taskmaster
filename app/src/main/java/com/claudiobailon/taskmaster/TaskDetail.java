package com.claudiobailon.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class TaskDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        TextView taskTitle = findViewById(R.id.taskTitleText);
        TextView taskBody = findViewById(R.id.taskBodyText);
        TextView taskState = findViewById(R.id.taskStateText);

        taskTitle.setText(intent.getExtras().getString("title"));
        taskBody.setText(intent.getExtras().getString("body"));
        taskState.setText(intent.getExtras().getString("state"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(TaskDetail.this, MainActivity.class);
        TaskDetail.this.startActivity(intent);
        return true;
    }

}