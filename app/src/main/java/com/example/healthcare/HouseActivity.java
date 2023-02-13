package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HouseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);

        SharedPreferences sp=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username=sp.getString("username"," ").toString();
        Toast.makeText(getApplicationContext(), "Welcome"+username, Toast.LENGTH_SHORT).show();
        //don't know why this username is not printing here.

        CardView exit=findViewById(R.id.cardLogout);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor ed=sp.edit();
                ed.clear();
                ed.apply();
                startActivity(new Intent(HouseActivity.this,LoginActivity.class));
            }
        });

        CardView findDoctor=findViewById(R.id.cardHealthDoctor);
        findDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(HouseActivity.this,find_Doctor_Activity.class));
            }
        });

        CardView labTest=findViewById(R.id.cardLabTest);
        labTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HouseActivity.this,LabTestActivity.class));
            }
        });

        CardView orderDetail=findViewById(R.id.cardOrderDetail);
        orderDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HouseActivity.this,OrderDetailsActivity.class));
            }
        });

        CardView buymedicine=findViewById(R.id.cardBuyMedicine);
        buymedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HouseActivity.this,BuyMedicineActivity.class));
            }
        });

        CardView healtharticles=findViewById(R.id.cardHealthCalendar);
        healtharticles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HouseActivity.this,HealthArticlesActivity.class));
            }
        });
    }
}