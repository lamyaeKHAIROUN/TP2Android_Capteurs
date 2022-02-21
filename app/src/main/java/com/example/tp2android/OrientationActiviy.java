package com.example.tp2android;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrientationActiviy extends AppCompatActivity implements SensorEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.orientation);


    }
    
    
        @Override
    public void onSensorChanged(SensorEvent event) {
            // Le SensorManager
            SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

            // Le capteur d'orientation
            Sensor orientation = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
            float x = 0,y = 0,z = 0;
            Log.v("change","change1");
            TextView azimuth = findViewById(R.id.azimuth);


            if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
                //l' azimuth
                 x = event.values[0];
                //le pitch
                 y = event.values[1];
                //le roll
                 z = event.values[2];

               Log.v("change","change");
            }
            azimuth.setText("Azimuth: "+x+"°");
            ((TextView)findViewById(R.id.pitch)).setText("Pitch: "+y+"°");
            ((TextView)findViewById(R.id.roll)).setText("Roll: "+z+"°");

        }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
