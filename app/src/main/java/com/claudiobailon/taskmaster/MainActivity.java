package com.claudiobailon.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button goToAddTask = MainActivity.this.findViewById(R.id.addTask);
        goToAddTask.setOnClickListener((view)-> {
            System.out.println("Going to Add Task");
            Intent goToAddTaskIntent = new Intent(MainActivity.this, AddTask.class);
            MainActivity.this.startActivity(goToAddTaskIntent);
        });

        Button goToAllTasks = MainActivity.this.findViewById(R.id.allTasks);
        goToAllTasks.setOnClickListener((view)-> {
            System.out.println("Going to All Tasks");
            Intent goToAllTasksIntent = new Intent(MainActivity.this, AllTasks.class);
            MainActivity.this.startActivity(goToAllTasksIntent);
        });
    }


}