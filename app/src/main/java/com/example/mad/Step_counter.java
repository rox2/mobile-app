package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Step_counter extends AppCompatActivity implements SensorEventListener {

    TextView tv_steps;
    SensorManager sensorManager;
    boolean running = false;

    /////////////////////////////
    EditText txtphone,cal;
    Button btnsave;
    Button  btndlt;
    DatabaseReference reff;
    Member member;


    /////////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);

        tv_steps = (TextView) findViewById(R.id.tv_steps);

        sensorManager =(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        /////////////////////////////////////////////////////////////////
        txtphone=(EditText)findViewById(R.id.txtphone);
        btnsave= (Button) findViewById(R.id.btnsave);
        btndlt= (Button) findViewById(R.id.btndlt);
        cal=(EditText)findViewById(R.id.cal);

        member=new Member();

        reff= FirebaseDatabase.getInstance().getReference().child("goal_step");

        btnsave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                float hit = Float.parseFloat((txtphone.getText().toString().trim()));
                Editable sum=cal.getText();
                Toast.makeText(Step_counter.this, sum, Toast.LENGTH_SHORT).show();
                member.setHt(hit);

                 reff.child("step 1").setValue(member);
                Toast.makeText(Step_counter.this, "successfully inserted", Toast.LENGTH_SHORT).show();
            }
        });

        btndlt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                member.setHt(0);

                reff.child("step 1").setValue(member);
                Toast.makeText(Step_counter.this, "successfully deleted", Toast.LENGTH_SHORT).show();

            }


        });




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
            tv_steps.setText(String.valueOf(event.values[1]));

        }
    }

    public void onAccuracyChanged(Sensor sensor,int accuracy){

    }
//test


}