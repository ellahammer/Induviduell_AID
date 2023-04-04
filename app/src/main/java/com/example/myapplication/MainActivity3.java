package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "MainActivity";
    private SensorManager sensorManager;
    private Sensor mySensor;
    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Log.v(TAG, "Kan skriva loggar");

        //textView = findViewById(R.id.monster_layout);
       // textView.setText("HejHej");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mySensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if(mySensor == null){
            Toast.makeText(this, "Service not detected", Toast.LENGTH_SHORT).show();
        }else{
            sensorManager.registerListener(MainActivity3.this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float EG = SensorManager.GRAVITY_EARTH;
            float accel = (sensorEvent.values[0] * sensorEvent.values[0]+
                    sensorEvent.values[1] * sensorEvent.values[1] +
                    sensorEvent.values[2] * sensorEvent.values[2] ) / (EG*EG);
            if (accel >= 1) {
                Log.i(TAG, "Inne i accelerometer");
                //textView.setText("HejHej");
               //// textView.setBackgroundColor(Color.GREEN);
               //imageView.setImageResource(R.drawable.sad);
            }
            if(sensorEvent.values[1] == android.view.Surface.ROTATION_180){
                Log.i(TAG, "Inne i rotation");
            }
               // textView.setText("HejHej");
                //imageView.setImageResource(R.drawable.sad);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}