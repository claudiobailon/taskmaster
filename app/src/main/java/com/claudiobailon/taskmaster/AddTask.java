package com.claudiobailon.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;

public class AddTask extends AppCompatActivity {

//    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);


        Button addButton = AddTask.this.findViewById(R.id.addTaskNow);
        addButton.setOnClickListener(this::onClick);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(AddTask.this, MainActivity.class);
        AddTask.this.startActivity(intent);
        return true;
    }

    private void onClick(View view) {

        Toast.makeText(getApplicationContext(), "Task Added!", Toast.LENGTH_SHORT).show();

//            db = Room.databaseBuilder(getApplicationContext(),Database.class, "claudiobailon_task_master")
//                    .allowMainThreadQueries()
//                    .build();

        EditText title = findViewById(R.id.taskTitle);
        EditText body = findViewById(R.id.taskDescription);

        Task task = Task.builder()
                .title(title.getText().toString())
                .body(body.getText().toString())
                .state("new")
                .build();
//          db.taskDAO().saveTask(task);

         Amplify.API.mutate(ModelMutation.create(task),
            success -> Log.i("AddTaskAmplify", "Task Added"),
            error -> Log.e("AddTaskAmplify", error.toString()));

    }
}