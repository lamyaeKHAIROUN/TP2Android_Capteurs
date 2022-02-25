package com.example.tp2android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;

public class Secouer extends AppCompatActivity implements SensorEventListener  {

    private SensorManager mSensorManager = null;
    private Sensor mAccelerometer = null;
    private boolean itIsNotFirstTime=false;
    private boolean itIsNotSecondTime=false;
    private Switch flashControl;
    private CameraManager cameraManager;
    private TextView currXText, currYText, currZText, lastXText, lastYText, lastZText,flashText;
    private float prevX;
    private float prevY;
    private float prevZ;
    private float changeValueX;
    private float changeValueY;
    private float changeValueZ;
    private float secouChangeValue=10;
    private float prev2X;
    private float prev2Y;
    private float prev2Z;
    private float changeValueX2,changeValueY2,changeValueZ2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secouer);
        currXText =findViewById(R.id.currx);
        currYText =findViewById(R.id.curry);
        currZText =findViewById(R.id.currz);
        lastXText =findViewById(R.id.lastx);
        lastYText =findViewById(R.id.lasty);
        lastZText =findViewById(R.id.lastz);
        flashText=findViewById(R.id.flash);
        cameraManager= (CameraManager) getSystemService(CAMERA_SERVICE);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("MissingPermission")
    @Override
    public void onSensorChanged(SensorEvent event) {
        float x, y, z;
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            x = event.values[0];
            y = event.values[1];
            z = event.values[2];
            currXText.setText("Current X:" +(int)x );
            currYText.setText("Current Y:" +(int)y );
            currZText.setText("Current Z:" +(int)z );
            if(itIsNotFirstTime){
                 changeValueX=Math.abs(x-prevX);
                 changeValueY=Math.abs(y-prevY);
                 changeValueZ=Math.abs(z-prevZ);

                 if((changeValueX>secouChangeValue&&changeValueY>secouChangeValue)||
                    (changeValueX>secouChangeValue&&changeValueZ>secouChangeValue)||
                     (changeValueY>secouChangeValue&&changeValueZ>secouChangeValue)
                 ){
                  //   vibrator.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE));
                     lastXText.setBackgroundColor(Color.RED);
                     lastYText.setBackgroundColor(Color.RED);
                     lastZText.setBackgroundColor(Color.RED);

                     lastXText.setText("Change value X:" +(int)changeValueX );
                     lastYText.setText("Change value Y:" +(int)changeValueY );
                     lastZText.setText("Change value Z:" +(int)changeValueZ );
                     flashText.setText("Flash On");
                     flashText.setBackgroundColor(Color.YELLOW);


                     try {
                         cameraManager.setTorchMode("0",true);
                     } catch (CameraAccessException e) {
                         e.printStackTrace();
                     }
                 }

            }

            prevX=x;
            prevY=y;
            prevZ=z;
            itIsNotFirstTime=true;

            if(itIsNotSecondTime){
                changeValueX2=Math.abs(prev2X-prevX);
                changeValueY2=Math.abs(prev2Y-prevY);
                changeValueZ2=Math.abs(prevZ-prevZ);

                if((changeValueX2>secouChangeValue&&changeValueY2>secouChangeValue)||
                        (changeValueX2>secouChangeValue&&changeValueZ2>secouChangeValue)||
                        (changeValueY2>secouChangeValue&&changeValueZ2>secouChangeValue)
                ){
                    //   vibrator.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE));
                    lastXText.setBackgroundColor(Color.GREEN);
                    lastYText.setBackgroundColor(Color.GREEN);
                    lastZText.setBackgroundColor(Color.GREEN);

                    lastXText.setText("Change value X:" +(int)changeValueX );
                    lastYText.setText("Change value Y:" +(int)changeValueY );
                    lastZText.setText("Change value Z:" +(int)changeValueZ );
                    flashText.setText("Flash Off");
                    flashText.setBackgroundColor(Color.YELLOW);

                    try {
                        cameraManager.setTorchMode("0",false);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }

                }

            }

            prev2X=prevX;
            prev2Y=prevY;
            prev2Z=prevZ;
            itIsNotSecondTime=true;

        }

    }
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}