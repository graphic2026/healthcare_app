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

public class BuyMedicineActivity extends AppCompatActivity {

    String[][] medicines = {
            {"Paracetamol", "","","","10.00"},
            {"Ibuprofen","","","", "15.00"},
            {"Amoxicillin","","","", "20.00"},
            {"Cetirizine","","","", "25.00"},
            {"Metformin","","","", "30.00"},
            {"Diclofenac","","","", "35.00"},
            {"Loratadine","","","", "40.00"},
            {"Prednisone", "","","","45.00"},
            {"Azithromycin","","","", "50.00"},
            {"Clindamycin","","","", "55.00"}
    };

    private String[]  packagesdetails= {
            "Building and keeping the bones & teeth strongin\n" +
                    "Reducing Fatigue/stress and suncular pains\n" +
                    "Boosting immunity and increasing resistance against infection",
            "Chromium is an essential trace sineral that plays an important role in hetaiing in regul" ,
            " Provides relief from vitamin deficienciesin. \n" +
                    "Helps in formation of red blood cells\n" +
                    "Maintains healthy nervous system",
            "It promotes health as well as skin benefit.\n" +
                    "It helps reduce skin blanish and pigeantation.\n " +
                    "It act as safegused the skin from the harsh UVA and UVB sus rays.",
            "Ole 658 Tablet helps relieve pain and fever by Blacking the release of certain chandel" ,
            "Helps relieve fever and bring duen a high temperatures\n" +
                    "Suitable for people with a heart condition or high stand pressure ",
            "Believes the symptoss of a bacterial threat infection and soothes the recovery prac\n" +
                    "Provides ware and conforting feeling during sore throat",
            "Reduces the risk of calcium deficiency, Ricasts, and steepresisin\n" +
                    "Promates mobility and flexibility of joints",
            "Helps to reduce the iron deficiency due to chronic blood loss a Loe intake of iron"
    };

   HashMap<String,String> hm;
   ArrayList list;
   SimpleAdapter sa;
   ListView listView;
   Button btn,gctbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        listView=findViewById(R.id.listViewBM);
        btn=findViewById(R.id.buttonbBM);
        gctbtn=findViewById(R.id.buttonBM);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this,HouseActivity.class));
            }
        });

        gctbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class));
            }
        });

        list=new ArrayList();
        for(int i=0;i<medicines.length;i++){
            hm=new HashMap<String,String>();
            hm.put("line1",medicines[i][0]);
            hm.put("line2",medicines[i][1]);
            hm.put("line3",medicines[i][2]);
            hm.put("line4",medicines[i][3]);
            hm.put("line5",medicines[i][4]);
            list.add(hm);
        }

        sa=new SimpleAdapter(this,list,R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(BuyMedicineActivity.this,BuyMedicineDetailsActivity.class);
                it.putExtra("text1",medicines[i][0]);
                it.putExtra("text2",packagesdetails[i]);
                it.putExtra("text3",medicines[i][4]);
                startActivity(it);
            }
        });
    }
}