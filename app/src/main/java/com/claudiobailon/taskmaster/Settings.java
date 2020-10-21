package com.claudiobailon.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor preferenceEditor = preferences.edit();

        Button submitUsername = findViewById(R.id.saveUsername);
        submitUsername.setOnClickListener((view) -> {

            EditText username = findViewById(R.id.username);
            preferenceEditor.putString("username", username.getText().toString());
            preferenceEditor.apply();
            Intent goHomeIntent = new Intent(Settings.this, MainActivity.class);
            Settings.this.startActivity(goHomeIntent);
            Toast.makeText(getApplicationContext(), "Username Added!", Toast.LENGTH_SHORT).show();

        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(Settings.this, MainActivity.class);
        Settings.this.startActivity(intent);
        return true;
    }

}