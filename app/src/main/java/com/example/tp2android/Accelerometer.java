package com.example.tp2android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Accelerometer extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager = null;
    private Sensor mAccelerometer = null;

    private TextView fondEcran =null;
    private TextView noir=null;
    private TextView rouge=null;
    private TextView accelerationText=null;
    private double acceleration;
    private double prevAcceleration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.accelerometer);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        fondEcran =findViewById(R.id.fongE);
        accelerationText=findViewById(R.id.acceleration);
        SensorManager sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);

    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }


    @SuppressLint("SetTextI18n")
    @Override
        public void onSensorChanged(SensorEvent event) {
        float x, y, z;
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            x = event.values[0];
            y = event.values[1];
            z = event.values[2];

            acceleration=Math.sqrt((x*x+y*y+z*z));
           double changeValue=Math.abs(acceleration-prevAcceleration);
            prevAcceleration=acceleration;
            accelerationText.setText("Acceleration: "+(int)acceleration+"\n" +
                   "Previous Accel: "+(int)prevAcceleration+"\n" +
                   "Change value: "+(int)changeValue);
            if(changeValue>5) fondEcran.setBackgroundColor(Color.GREEN);
            else  if(changeValue>2) fondEcran.setBackgroundColor(Color.BLACK);
            else if (changeValue>0) fondEcran.setBackgroundColor(Color.RED);



        }
        }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


}

