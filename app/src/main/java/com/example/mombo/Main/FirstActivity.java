package com.example.mombo.Main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.mombo.R;


public class FirstActivity extends Activity implements SensorEventListener, View.OnClickListener {

    TextView tv_steps;
    SensorManager sensorManager;
    ImageButton btnHome, btnCamera, btnTalk, btnRecord;

    boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);


        btnHome = (ImageButton) findViewById(R.id.btnCommonHome);
        btnCamera = (ImageButton) findViewById(R.id.btnCommonCamera);
        btnTalk = (ImageButton) findViewById(R.id.btnCommonTalk);
        btnRecord = (ImageButton) findViewById(R.id.btnCommonRecord);
        tv_steps = (TextView) findViewById(R.id.tv_steps);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        ImageButton Button2 = (ImageButton) findViewById(R.id.myaccount);

        btnCamera.setOnClickListener(this);
        btnRecord.setOnClickListener(this);
        btnTalk.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnCommonCamera:
                startActivity(new Intent(FirstActivity.this, CameraActivity.class));
                break;
            case R.id.btnCommonTalk :
                startActivity(new Intent(FirstActivity.this, ChattingActivity.class));
                break;
            case  R.id.btnCommonRecord :
                startActivity(new Intent(FirstActivity.this, CheckActivity.class));
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "센서가 이상해요", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        running = false;
        //sensorManager.registerListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (sensorManager != null)
            sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (running) {
            tv_steps.setText(String.valueOf(event.values[0]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}


