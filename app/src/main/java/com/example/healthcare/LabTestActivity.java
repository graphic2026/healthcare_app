package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends AppCompatActivity {

    private String[][] packages={
            {"Packages 1 : Full Body Checkup"," "," "," ","999"},
            {"Packages 2 : Blood Glucose Fasting"," "," "," ","299"},
            {"Packages 3 : Covid-19 Antibody "," "," "," ","899"},
            {"Packages 4 : Thyroid checkup"," "," "," ","499"},
            {"Packages 5 : Immunity Checkup"," "," "," ","699"}
    };

    private String[] packages_details={
"Blood Glucose Fasting\n"+
                    "Complete Hemogram\n"+
                    "HbA1c\n" +
                     "Iron Studies\n" +
                    "Kidney Function Testin"+
                    "LDH Lactate Dehydrogenase, Serumin \n" +
                    "Lipid Profile\n"+
                    "Liver Function Test",

            "Thyroid Profile-Total (T3, T4 & TSH Ultra-sensitive)",
            "Complete Hemogram\n" +
                    "CRP (C Reactive Protein) Quantitative, Serumin\n"+
                    "Iron Studies\n" +
                    "Kidney Function Test\n"+
                    "Vitamin D Total-25 Hydroxy\n" +
                    "Liver Function Testin\n" +
                    "Lipid Profile",
            "Complete Hemogram\n" +
                    "CRP (C Reactive Protein) Quantitative, Serumin\n"+
                    "Iron Studies\n" +
                    "Kidney Function Test\n"+
                    "Vitamin D Total-25 Hydroxy\n" +
                    "Liver Function Testin\n" +
                    "Lipid Profile",
            "Complete Hemogram\n" +
                    "CRP (C Reactive Protein) Quantitative, Serumin\n"+
                    "Iron Studies\n" +
                    "Kidney Function Test\n"+
                    "Vitamin D Total-25 Hydroxy\n" +
                    "Liver Function Testin\n" +
                    "Lipid Profile"
    };

ArrayList list=new ArrayList();
SimpleAdapter sa;
ListView listView;
Button btn,gtcbtn;
HashMap<String,String>  hm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        listView=findViewById(R.id.LTlistView);
        btn=findViewById(R.id.LTbackbutton);
        gtcbtn=findViewById(R.id.gtcbutton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this,HouseActivity.class));
            }
        });


//
        for(int i=0;i<packages.length;i++){
            hm=new HashMap<String,String>();
            hm.put("line1",packages[i][0]);
            hm.put("line2",packages[i][1]);
            hm.put("line3",packages[i][2]);
            hm.put("line4",packages[i][3]);
            hm.put("line5","Cost :"+packages[i][4]);
            list.add(hm);
        }
        sa=new SimpleAdapter(this,list,R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(LabTestActivity.this,LabTestDetailsActivity.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",packages_details[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);
            }
        });

        gtcbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this,CardLabActivity.class));
            }
        });

    }
}