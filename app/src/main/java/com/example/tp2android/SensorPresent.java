package com.example.tp2android;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SensorPresent extends AppCompatActivity {
    SensorManager mSensorManager;
    SensorManager mySensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_present);


        TextView magnetic = findViewById(R.id.magnitic);
        TextView proximity = (TextView) findViewById(R.id.proximity);

        //    listSensor();

        // Capteur de magnetic
        mSensorManager = (SensorManager)
                getSystemService(Context.SENSOR_SERVICE);
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
                != null) {
            magnetic.setText("Success! There's a magnetometer sensor");
            Log.v("presence de capteur", "presence de capt");
        } else {
            magnetic.setText("Failure! No magnetometer sensor");
            Log.v("absence de capteur", "absence de capt");

        }
        // Capteur de proximity
        mySensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (mySensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
                != null) {
            proximity.setText("Success! There's a proximity sensor");
            Log.v("presence de proximity", "presence de proximity");
        } else {
            proximity.setText("Failure! No proximity sensor");
            Log.v("absence de proximity", "absence de proximity");

        }

    }}