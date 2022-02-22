package com.example.tp2android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SensorManager mSensorManager2;
    SensorManager mySensorManager;
     Display mDisplay;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnBasculer = (Button) findViewById(R.id.monBouton);

        btnBasculer
                .setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent1 = new Intent(MainActivity.this, SensorList.class);
                        startActivity(intent1);
                    }
                });

        Button btn2 = (Button) findViewById(R.id.monBouton2);

        btn2
                .setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent1 = new Intent(MainActivity.this, SensorPresent.class);
                        startActivity(intent1);
                    }
                });

        Button btn3 = (Button) findViewById(R.id.monBouton3);

        btn3
                .setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent1 = new Intent(MainActivity.this, Accelerometer.class);
                        startActivity(intent1);
                    }
                });


        Button btn4 = (Button) findViewById(R.id.monBouton4);

        btn4.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent1 = new Intent(MainActivity.this, Direction.class);
                        startActivity(intent1);
                    }
                });
    }
}



