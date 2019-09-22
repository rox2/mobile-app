package com.example.mad;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class datainsert  extends AppCompatActivity {

EditText txtphone;
Button btnsave;
DatabaseReference reff;
Member member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);

        txtphone=(EditText)findViewById(R.id.txtphone);
        btnsave= (Button) findViewById(R.id.btnsave);
        member=new Member();

        reff= FirebaseDatabase.getInstance().getReference().child("step");
        btnsave.setOnClickListener(new View.OnClickListener(){
             public void onClick(View view){
                 float hit = Float.parseFloat((txtphone.getText().toString().trim()));
                 member.setHt(hit);

                 reff.push().setValue(member);
                 Toast.makeText(datainsert.this, "data insert successful", Toast.LENGTH_SHORT).show();
             }
        });
    }

}
