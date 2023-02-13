package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class find_Doctor_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);

        CardView back=findViewById(R.id.cardBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(find_Doctor_Activity.this,HouseActivity.class));
            }
        });

        CardView familyphysician=findViewById(R.id.cardFamilyPhysician);
        familyphysician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it =new Intent(find_Doctor_Activity.this,Doctor_Details_Activity.class);
                it.putExtra("title","Family Physician");
                startActivity(it);
            }
        });

        CardView dietician=findViewById(R.id.cardDietician);
        dietician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it =new Intent(find_Doctor_Activity.this,Doctor_Details_Activity.class);
                it.putExtra("title","Dietician");
                startActivity(it);
            }
        });

        CardView dentist=findViewById(R.id.cardDentist);
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it =new Intent(find_Doctor_Activity.this,Doctor_Details_Activity.class);
                it.putExtra("title","Dentist");
                startActivity(it);
            }
        });

        CardView surgeon=findViewById(R.id.cardSurgeon);
        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it =new Intent(find_Doctor_Activity.this,Doctor_Details_Activity.class);
                it.putExtra("title","Surgeon");
                startActivity(it);
            }
        });

        CardView Cardiologists=findViewById(R.id.cardCardiologists);
        Cardiologists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it =new Intent(find_Doctor_Activity.this,Doctor_Details_Activity.class);
                //putExtra take a extra msg with the intent
                it.putExtra("title","Cardiologists");
                startActivity(it);
            }
        });
    }
}