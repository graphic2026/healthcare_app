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
import java.util.List;

public class HealthArticlesActivity extends AppCompatActivity {
    private String[][] health_detail=
            {
                    {"Walking Daily","","","","Click more Details"},
                    {"Home care of Covid-19","","","","Click more Details"},
                    {"Stop Smoking","","","","Click more Details"},
                    {"Menstrual Cramps","","","","Click more Details"},
                    {"healthy Gut","","","","Click more Details"}
            };
    private int[] images={
            R.drawable.health1,
            R.drawable.health2,
            R.drawable.health3,
            R.drawable.health4,
            R.drawable.health5
    };
    HashMap<String,String> hm;
    ArrayList list;
    SimpleAdapter sa;
    Button back;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles);

        back=findViewById(R.id.buttonOD);
        listView=findViewById(R.id.listViewOD);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthArticlesActivity.this,HouseActivity.class));
            }
        });
            list=new ArrayList();
        for(int i=0;i< health_detail.length;i++){
            hm=new HashMap<String,String>();
            hm.put("line1",health_detail[i][0]);
            hm.put("line2",health_detail[i][1]);
            hm.put("line3",health_detail[i][2]);
            hm.put("line4",health_detail[i][3]);
            hm.put("line5",health_detail[i][4]);
            list.add(hm);
        }
        //important for the showing the details of the doctor in the listview using adapter
        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
//        ListView lst=findViewById(R.id.listView);
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(HealthArticlesActivity.this,HealthArticlesDetailsActivity.class);
//                it.putExtra("text1" ,title);
                it.putExtra("text1" ,health_detail[i][0]);
                it.putExtra("text2",images[i]);

//                it.putExtra("text3" ,doctor_details[i][1]);
//                it.putExtra("text4" ,doctor_details[i][3]);
//                it.putExtra("text5" ,doctor_details[i][4]);
                startActivity(it);
            }
        });
    }
}