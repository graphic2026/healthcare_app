package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderDetailsActivity extends AppCompatActivity {
    private String[][] orderdetails={};

    HashMap<String,String> hm;
    ArrayList list;
    SimpleAdapter sa;
    ListView listView;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        btn=findViewById(R.id.buttonOD);
        listView=findViewById(R.id.listViewOD);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderDetailsActivity.this,HouseActivity.class));
            }
        });

        Database db=new Database(getApplicationContext(),"healthcare",null,1);
        SharedPreferences sp=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username=sp.getString("username","").toString();
        ArrayList dbData=db.getOrderData(username);

        orderdetails =new String[dbData.size()][];
        for(int i=0;i<orderdetails.length;i++){
            orderdetails[i]=new String[5];
            String arrData=dbData.get(i).toString();
            String[] strData=arrData.split(java.util.regex.Pattern.quote("$"));
            orderdetails[i][0]=strData[0];
            orderdetails[i][1]=strData[1];

            if(strData[7].compareTo("medicine")==0){
                orderdetails[i][3]="Del:"+strData[4];
            }
            else{
                orderdetails[i][3]="Del:"+strData[4]+" "+strData[5];
            }
            orderdetails[i][2]="Rs."+strData[6];
            orderdetails[i][4]=strData[7];

        }
         list =new ArrayList();
        for(int i=0;i<orderdetails.length;i++){
            hm=new HashMap<String,String>();
            hm.put("line1",orderdetails[i][0]);
            hm.put("line2",orderdetails[i][1]);
            hm.put("line3",orderdetails[i][2]);
            hm.put("line4",orderdetails[i][3]);
            hm.put("line5",orderdetails[i][4]);
            list.add(hm);
        }
        sa=new SimpleAdapter(this,list,R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        listView.setAdapter(sa);

    }
}