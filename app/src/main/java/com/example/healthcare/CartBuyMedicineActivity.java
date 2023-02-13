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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Pattern;

public class CartBuyMedicineActivity extends AppCompatActivity {

    HashMap<String,String> hm;
    ArrayList list;
    SimpleAdapter sa;
    TextView textView;
    ListView listView;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dbtn,tbtn,bbtn,cbtn;
    private String[][] packages={};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_buy_medicine);

        dbtn=findViewById(R.id.datebutton);
        tbtn=findViewById(R.id.timebutton);
        bbtn=findViewById(R.id.CLAbackbutton);
        cbtn=findViewById(R.id.checkoutbutton);
        textView=findViewById(R.id.textViewCLAfee);
//        editText=findViewById(R.id.editTextmultilineCLA);
        listView=findViewById(R.id.CLAlistView);

        SharedPreferences sp=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username=sp.getString("username","").toString();

        Database db=new Database(getApplicationContext(),"healthcare",null,1);

        float totalAmount=0;
        ArrayList dbData=db.getCardData(username,"medicine");


        //for calculate the total amount of the selected packages
        packages=new String[dbData.size()][];
        for(int i=0;i<dbData.size();i++ ){
            packages[i]=new String[5];
        }
        for(int i=0;i<dbData.size();i++){
            String arrData=dbData.get(i).toString();
            String[] strData=arrData.split(Pattern.quote("$"));
            packages[i][0]=strData[0];
            packages[i][4]="Cost : "+strData[1]+"/-";
            totalAmount=totalAmount+Float.parseFloat(strData[1]);

        }
        textView.setText("Total Cost:"+totalAmount);


        list=new ArrayList();
        for(int i=0;i<packages.length;i++){
            hm=new HashMap<String,String>();
            hm.put("line1",packages[i][0]);
            hm.put("line2",packages[i][1]);
            hm.put("line3",packages[i][2]);
            hm.put("line4",packages[i][3]);
            hm.put("line5",packages[i][4]);
            list.add(hm);

        }

        sa=new SimpleAdapter(this,list,R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        listView.setAdapter(sa);

        cbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(CartBuyMedicineActivity.this,BuyMedicineBookActivity.class);
                it.putExtra("price",textView.getText());
                it.putExtra("date",dbtn.getText());
//                it.putExtra("time",tbtn.getText());
                startActivity(it);
            }
        });




        Toast.makeText(getApplicationContext(), ""+dbData   , Toast.LENGTH_SHORT).show();
        bbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartBuyMedicineActivity.this,BuyMedicineActivity.class));
            }
        });

        initDatePicker();
        dbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });


    }
    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker , int i, int i1, int i2){
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
}