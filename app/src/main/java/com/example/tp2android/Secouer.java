package com.example.tp2android;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Secouer extends AppCompatActivity implements SensorEventListener {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secouer);

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
        float x, y, z;
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            x = event.values[0];
            if(x>0){
            }
            else {

            }
            y = event.values[1];
            if(y>0) {

            }
            else {
            }
            z = event.values[2];
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}