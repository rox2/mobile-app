package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public Button step;

    public void intit(){
        step = (Button) findViewById(R.id.btn4);
        step.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent count =new Intent(MainActivity.this,Step_counter.class);

                startActivity(count);
            }
    });

}
//------------------------------------------------------------//
    //protected void



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(MainActivity.this,"connection sucess",Toast.LENGTH_LONG).show();

        intit();

    }
}
