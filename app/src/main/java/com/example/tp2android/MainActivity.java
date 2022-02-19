package com.example.tp2android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager mSensorManager2;
    SensorManager mySensorManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView magnetic = findViewById(R.id.magnitic);
        TextView proximity = (TextView) findViewById(R.id.proximity);
        ImageView verte = findViewById(R.id.verte);
        verte.setVisibility(View.INVISIBLE);
        ImageView noir = findViewById(R.id.noir);
        noir.setVisibility(View.INVISIBLE);
        ImageView rouge = findViewById(R.id.rouge);
        rouge.setVisibility(View.INVISIBLE);

        //    listSensor();

        // Capteur de ACCELEROMETER
        mSensorManager2 = (SensorManager)
                getSystemService(Context.SENSOR_SERVICE);
        if (mSensorManager2.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
                != null) {
            magnetic.setText("Success! There's a ACCELEROMETER sensor");
            Log.v("presence de capteur", "presence de capt");
        } else {
            magnetic.setText("Failure! No ACCELEROMETER sensor");
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
//Accelerometer
        SensorManager sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);

        Boolean supported = sensorMgr.registerListener((SensorEventListener) this,
                sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);
        if (!supported) {
            sensorMgr.unregisterListener
                    ((SensorEventListener) this,sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
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

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        ImageView verte = findViewById(R.id.verte);
        ImageView noir = findViewById(R.id.noir);
        ImageView rouge = findViewById(R.id.rouge);
        switch (accuracy) {
            case SensorManager.SENSOR_STATUS_ACCURACY_HIGH:
                verte.setVisibility(View.VISIBLE);
                noir.setVisibility(View.INVISIBLE);
                rouge.setVisibility(View.INVISIBLE);
            case SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM:
                verte.setVisibility(View.INVISIBLE);
                noir.setVisibility(View.VISIBLE);
                rouge.setVisibility(View.INVISIBLE);
            case SensorManager.SENSOR_STATUS_ACCURACY_LOW:
                verte.setVisibility(View.INVISIBLE);
                noir.setVisibility(View.INVISIBLE);
                rouge.setVisibility(View.VISIBLE);

        }
        Log.d("Sensor", sensor.getType() + ":" + accuracy);
    }
}



