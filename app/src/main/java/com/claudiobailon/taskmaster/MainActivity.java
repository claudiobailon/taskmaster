package com.claudiobailon.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onResume() {

        super.onResume();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        TextView username = findViewById(R.id.displayUsername);
        username.setText(preferences.getString("username", "Go to Settings to create username"));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //state types are : new, assigned, in progress, and complete
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Mow", "Mow the lawn","assigned"));
        tasks.add(new Task("Wash Dishes", "Wash the dishes","in progress"));
        tasks.add(new Task("Trash", "Take out the trash","complete"));
        tasks.add(new Task("Exercise", "Get that body movin!","new"));
        tasks.add(new Task("Mow", "Mow the lawn","assigned"));
        tasks.add(new Task("Wash Dishes", "Wash the dishes","in progress"));
        tasks.add(new Task("Trash", "Take out the trash","complete"));
        tasks.add(new Task("Exercise", "Get that body movin!","new"));
        tasks.add(new Task("Mow", "Mow the lawn","assigned"));
        tasks.add(new Task("Wash Dishes", "Wash the dishes","in progress"));
        tasks.add(new Task("Trash", "Take out the trash","complete"));
        tasks.add(new Task("Exercise", "Get that body movin!","new"));
        tasks.add(new Task("Mow", "Mow the lawn","assigned"));
        tasks.add(new Task("Wash Dishes", "Wash the dishes","in progress"));
        tasks.add(new Task("Trash", "Take out the trash","complete"));
        tasks.add(new Task("Exercise", "Get that body movin!","new"));

        RecyclerView recyclerView = findViewById(R.id.tasksListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TaskAdapter(tasks, this::listener));

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


// can this be made more efficient?
//
//        Button mowButton = findViewById(R.id.mowTask);
//        mowButton.setOnClickListener((view)-> {
//            System.out.println("Going to Task Details");
//            Button taskButton = (Button) view;
//            Intent goToTaskDetailIntent = new Intent(MainActivity.this, TaskDetail.class);
//            goToTaskDetailIntent.putExtra("task", taskButton.getText().toString());
//            MainActivity.this.startActivity(goToTaskDetailIntent);
//        });
//
//        Button washButton = findViewById(R.id.washTask);
//        washButton.setOnClickListener((view)-> {
//            System.out.println("Going to Task Details");
//            Button taskButton = (Button) view;
//            Intent goToTaskDetailIntent = new Intent(MainActivity.this, TaskDetail.class);
//            goToTaskDetailIntent.putExtra("task", taskButton.getText().toString());
//            MainActivity.this.startActivity(goToTaskDetailIntent);
//        });
//
//        Button trashButton = findViewById(R.id.trashTask);
//        trashButton.setOnClickListener((view)-> {
//            System.out.println("Going to Task Details");
//            Button taskButton = (Button) view;
//            Intent goToTaskDetailIntent = new Intent(MainActivity.this, TaskDetail.class);
//            goToTaskDetailIntent.putExtra("task", taskButton.getText().toString());
//            MainActivity.this.startActivity(goToTaskDetailIntent);
//        });
    }

//    @Override
    public void listener(Task task){
        Intent goToTaskDetailIntent = new Intent(MainActivity.this, TaskDetail.class);
        goToTaskDetailIntent.putExtra("title", task.title);
        goToTaskDetailIntent.putExtra("body", task.body);
        goToTaskDetailIntent.putExtra("state", task.state);
        this.startActivity(goToTaskDetailIntent);
    }


}