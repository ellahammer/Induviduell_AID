package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor mySensor;
    private ImageView imageView;
    private TextView textView;
    private TextView headerText;
    private long lastUpdate, actualTime;
    private Vibrator vibrator;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        headerText = findViewById(R.id.happy_header);
        textView = findViewById(R.id.textViewMonster);
        mediaPlayer = MediaPlayer.create(this, R.raw.angry);
        lastUpdate = System.currentTimeMillis();
        imageView = findViewById(R.id.happyMonster);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mySensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        //Check if the device has sensor service
        if(mySensor == null){
            Toast.makeText(this, "Service not detected", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            sensorManager.registerListener(MainActivity3.this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        Button resetMonster = findViewById(R.id.resetMonster);
        resetMonster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setBackgroundResource(R.color.monsterBackgroundFirst);
                imageView.setImageResource(R.drawable.happymonster);
                headerText.setText("Happy Monster");
                textView.setText("Shake me if you dare!");
                vibrator.cancel();
                mediaPlayer.stop();
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float EG = SensorManager.GRAVITY_EARTH;
            float accel = (sensorEvent.values[0] * sensorEvent.values[0]+
                    sensorEvent.values[1] * sensorEvent.values[1] +
                    sensorEvent.values[2] * sensorEvent.values[2] ) / (EG*EG);
            //textView.setText("Accelerometer: "  + String.valueOf(accel));
            if (accel >= 1.000687 ) { //Change to 1.5 on a mobile deice
                lastUpdate = actualTime;
                textView.setBackgroundColor(Color.GREEN);
                imageView.setImageResource(R.drawable.sadmonster);
                headerText.setText("Angry Monster");
                vibrator.vibrate((long) 100);
                textView.setText("");
                mediaPlayer.start();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}