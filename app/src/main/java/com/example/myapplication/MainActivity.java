package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  {

    private Button acce_button;
    private Button monster_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "The mail function will be implemented in the future.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Open Accelerometer activity
        acce_button = (Button) findViewById(R.id.acce_button);
        acce_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openActivityAcce();
            }
        });
        //Open Monster game activity
        monster_button = (Button) findViewById(R.id.monster_button);
        monster_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openActivityMonster();
            }
        });
    }

    public void openActivityAcce() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    public void openActivityMonster() {
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}