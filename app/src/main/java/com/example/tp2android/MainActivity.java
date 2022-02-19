package com.example.tp2android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
     SensorManager mSensorManager;
     SensorManager mySensorManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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





    }

    /*
   private void listSensor() {
       setContentView(R.layout.activity_main);
       SensorManager sensorManager = (SensorManager)
               getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuffer sensorDesc = new StringBuffer();
        for (Sensor sensor : sensors) {
            sensorDesc.append("New sensor detected : \r\n");
            sensorDesc.append("\tName: " + sensor.getName() + "\r\n");
            sensorDesc.append("\tType: " + sensor.getType() + "\r\n");
            sensorDesc.append("Version: " + sensor.getVersion() + "\r\n");
            sensorDesc.append("Resolution (in the sensor unit): " +
                    sensor.getResolution() + "\r\n");
            sensorDesc.append("Power in mA used by this sensor while in use" +
                    sensor.getPower() +"\r\n");
            sensorDesc.append("Vendor: " + sensor.getVendor() + "\r\n");
            sensorDesc.append("Maximum range of the sensor in the sensor's unit." +
                    sensor.getMaximumRange() + "\r\n");
            sensorDesc.append("Minimum delay allowed between two events in microsecond " + " or zero if this sensor only returns a value when the data it's measuring changes " +
            sensor.getMinDelay() + "\r\n");
        }
        Toast.makeText(this, sensorDesc.toString(), Toast.LENGTH_LONG).show();
    }*/
}




