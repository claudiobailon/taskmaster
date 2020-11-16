package com.claudiobailon.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TaskAdapter.InteractWithTaskListener {

//    Database db;
//    ArrayList<Task> tasks = (ArrayList<Task>) db.taskDAO().getRecentTasks();
    ArrayList<Task> tasks = new ArrayList<>();
    Handler handleTasks;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        db = Room.databaseBuilder(getApplicationContext(), Database.class, "claudiobailon_task_master")
//                .allowMainThreadQueries()
//                .build();

        configureAws();
        setupRecyclerView();
        getFromAws();


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
    public void onResume() {

        super.onResume();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        TextView username = findViewById(R.id.displayUsername);
        username.setText(preferences.getString("username", "Go to Settings to create username"));

//        db = Room.databaseBuilder(getApplicationContext(), Database.class, "claudiobailon_task_master")
//                .allowMainThreadQueries()
//                .build();


//        setupRecyclerView();

    }

    //===================== AWS ======================
    public void configureAws(){
        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());

            Log.i("MainActivityAmplify", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MainActivityAmplify", "Amplify not initialized", error);
        }
    }

    public void getFromAws(){

        Handler handleTasks = new Handler(Looper.getMainLooper(), message -> {
//            new Handler.Callback(){
//                @Override
//                public boolean handleMessage(@NonNull Message Message){
//                    recyclerView.getAdapter().notifyDataSetChanged();
//                    return false;
//                }
//            }
            setupRecyclerView();
            return false;
        });

        Amplify.API.query(
                ModelQuery.list(Task.class),
                success -> {
                    for (Task task : success.getData()) {
                        tasks.add(task);
                    }
                    handleTasks.sendEmptyMessage(1);
                    Log.i("AmplifyQuery", "Got this many items from dynamoDB " + tasks.size());
                },
                error -> Log.i("AmplifyQuery", "No items received")
        );
//        Handler handleTasksAdded = new Handler(Looper.getMainLooper(),
//                (message -> {
//                    recyclerView.getAdapter().notifyItemInserted(tasks.size()-1);
//                    Toast.makeText(
//                            this,
//                            tasks.get(tasks.size()-1).title+ " has been added.",
//                            Toast.LENGTH_SHORT).show();
//                    return false;
//
//                })
//        );
    }
    //==========================================
//=========ReycylerView things=================================

    public void setupRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.tasksListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TaskAdapter(tasks, this));
    }
    //=========================================

    @Override
    public void listener(Task task){
        Intent goToTaskDetailIntent = new Intent(MainActivity.this, TaskDetail.class);
        goToTaskDetailIntent.putExtra("title", task.title);
        goToTaskDetailIntent.putExtra("body", task.body);
        goToTaskDetailIntent.putExtra("state", task.state);
        this.startActivity(goToTaskDetailIntent);
    }


}