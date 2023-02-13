package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Book_Apointment_Activity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4;
    TextView tv;
    private Button rbtn;
   private Button btn;
   private DatePickerDialog datePickerDialog;
   private TimePickerDialog timePickerDialog;
   private Button dbtn,tbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_apointment);

        ed1=findViewById(R.id.editTextUsername);
        ed2=findViewById(R.id.editTextAddress);
        ed3=findViewById(R.id.editTextContact);
        ed4=findViewById(R.id.editTextFees);
        btn=findViewById(R.id.backbutton);
        dbtn=findViewById(R.id.datebutton);
        tbtn=findViewById(R.id.timebutton);
        rbtn=findViewById(R.id.registerbutton);

        tv=findViewById(R.id.textViewAppTitle);

        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it=getIntent();
        String title =it.getStringExtra("text1");
        String fullname =it.getStringExtra("text2");
        String address =it.getStringExtra("text3");
        String contact =it.getStringExtra("text4");
        String fees =it.getStringExtra("text5");

        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("cons fees:"+fees);

        initDatePicker();
        dbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
        initTimePicker();
        tbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Book_Apointment_Activity.this,find_Doctor_Activity.class));
            }
        });

        rbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                SharedPreferences sp=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sp.getString("username","").toString();

                if(db.checkAppointmentExists(username,title+" =>"+fullname,address,contact,dbtn.getText().toString(),tbtn.getText().toString())==1){
                    Toast.makeText(getApplicationContext(), "Appointment already booked", Toast.LENGTH_SHORT).show();
                }
                else{
                    db.addOrder(username,title+"=>"+fullname,address,contact,0,dbtn.getText().toString(),tbtn.getText().toString(),Float.parseFloat(fees),"appointment");
                    Toast.makeText(getApplicationContext(), "Your appointment is done successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Book_Apointment_Activity.this,HouseActivity.class));
                }
            }
        });


    }
    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker , int i,int i1,int i2){
                i1=i1+1;
                    dbtn.setText(i2+"/"+i1+"/"+i);
            }
        };
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);

        int style= AlertDialog.THEME_HOLO_DARK;
        datePickerDialog=new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);
    }


    private void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                           tbtn.setText(i+":"+i1);
            }
        };
        Calendar cal=Calendar.getInstance();
        int hrs=cal.get(Calendar.HOUR);
        int mins=cal.get(Calendar.MINUTE);

        int style=AlertDialog.THEME_HOLO_DARK;
        timePickerDialog=new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);
    }
}