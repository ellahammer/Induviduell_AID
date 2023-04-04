package com.example.myapplication;

import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "MainActivity";
    private SensorManager sensorManager;
    Sensor accelerometer;
    TextView xyzValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if(sensorManager != null){
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if(accelerometer != null){
                sensorManager.registerListener(MainActivity2.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            }
        }else{
            Toast.makeText(this, "Service not detected", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            ((TextView)findViewById(R.id.xyzValue)).setText(
                    "X: " + sensorEvent.values[0] +
                            " Y: " + sensorEvent.values[1] +
                            " Z: " + sensorEvent.values[1]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}