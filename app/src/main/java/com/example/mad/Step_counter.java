package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Step_counter extends AppCompatActivity implements SensorEventListener {

    TextView tv_steps;
    SensorManager sensorManager;
    boolean running = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);

        tv_steps = (TextView) findViewById(R.id.tv_steps);

        sensorManager =(SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }


    protected void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor != null){
            sensorManager.registerListener(this,countSensor,SensorManager.SENSOR_DELAY_UI);
        }
        else{
            Toast.makeText(this, "sensor not found", Toast.LENGTH_SHORT).show();
        }
    }

    protected  void onPause(){
        super.onPause();
        running = false;
    }
    @Override
    public void onSensorChanged(SensorEvent event){
        if(running){
            tv_steps.setText(String.valueOf(event.values[0]));

        }
    }

    public void onAccuracyChanged(Sensor sensor,int accuracy){

    }


}
