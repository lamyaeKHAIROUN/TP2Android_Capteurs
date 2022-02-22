package com.example.tp2android;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Accelerometer extends AppCompatActivity implements SensorEventListener {


    private SensorManager mSensorManager = null;
    private Sensor mAccelerometer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.accelerometer);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SensorManager sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);

        Boolean supported = sensorMgr.registerListener(this,
                sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);
        if (!supported) {
            sensorMgr.unregisterListener
                    (this,sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
        }

    }




    @Override
        public void onSensorChanged(SensorEvent event) {

            }



    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


}