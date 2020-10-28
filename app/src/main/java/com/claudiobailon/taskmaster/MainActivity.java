package com.claudiobailon.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TaskAdapter.InteractWithTaskListener {

    Database db;

    @Override
    public void onResume() {

        super.onResume();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        TextView username = findViewById(R.id.displayUsername);
        username.setText(preferences.getString("username", "Go to Settings to create username"));

        db = Room.databaseBuilder(getApplicationContext(), Database.class, "claudiobailon_task_master")
                .allowMainThreadQueries()
                .build();

        ArrayList<Task> tasks = (ArrayList<Task>) db.taskDAO().getRecentTasks();

        RecyclerView recyclerView = findViewById(R.id.tasksListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TaskAdapter(tasks, this));


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(), Database.class, "claudiobailon_task_master")
                .allowMainThreadQueries()
                .build();
//        Task mow = new Task("Mow", "Mow the lawn","Assigned");
//        db.taskDAO().saveTask(mow);
//        ArrayList<Task> tasks = (ArrayList<Task>) db.taskDAO().getRecentTasks();

        //state types are : new, assigned, in progress, and complete
//        tasks.add(new Task("Mow", "Mow the lawn","Assigned"));
//        tasks.add(new Task("Wash Dishes", "Wash the dishes","In Progress"));
//        tasks.add(new Task("Trash", "Take out the trash","Complete"));
//        tasks.add(new Task("Exercise", "Get that body movin!","New"));

//
//
//        RecyclerView recyclerView = findViewById(R.id.tasksListView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(new TaskAdapter(tasks, this));

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

        ImageButton goToSettings = MainActivity.this.findViewById(R.id.settingsButton);
        goToSettings.setOnClickListener((view)-> {
            System.out.println("Going to Settings");
            Intent goToSettingsIntent = new Intent(MainActivity.this, Settings.class);
            MainActivity.this.startActivity(goToSettingsIntent);
        });


    }

    @Override
    public void listener(Task task){
        Intent goToTaskDetailIntent = new Intent(MainActivity.this, TaskDetail.class);
        goToTaskDetailIntent.putExtra("title", task.title);
        goToTaskDetailIntent.putExtra("body", task.body);
        goToTaskDetailIntent.putExtra("state", task.state);
        this.startActivity(goToTaskDetailIntent);
    }


}