package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Doctor_Details_Activity extends AppCompatActivity {
    private String[][] indian_family= {
            {"Doctor Name: Dr. Smith", "Hospital Address :  123 Main St, Anytown USA 12345","Mobile no. : 555-555-1212","Exp : 10 years","100"},
            {"Doctor Name: Dr. Johnson","Hospital Address :  456 Park Ave, Anytown USA 12345","Mobile no. : 555-555-1213","Exp : 5 years","85"},
            {"Doctor Name: Dr. Williams","Hospital Address :  789 Elm St, Anytown USA 12345","Mobile no. : 555-555-1214","Exp : 15 years","110"},
            {"Doctor Name: Dr. Brown","Hospital Address :  101 First St, Anytown USA 12345","Mobile no. : 555-555-1215","Exp : 12 years","95"},
            {"Doctor Name: Dr. Davis","Hospital Address :  202 Second St, Anytown USA 12345","Mobile no. : 555-555-1216","Exp : 8 years","80"},
            { " Doctor Name: Dr. Patel", "Hospital Address : 123 Main St, Mumbai India 12345", "Mobile no. : 555-555-1212", "Exp : 10 years", "50"},
            {"Doctor Name: Dr. Kumar", "Hospital Address : 456 Park Ave, Delhi India 12345", "Mobile no. : 555-555-1213", "Exp : 5 years", "45"},
            {"Doctor Name: Dr. Singh", "Hospital Address : 789 Elm St, Bangalore India 12345", "Mobile no. : 555-555-1214", "Exp : 15 years", "60"},
            {"Doctor Name: Dr. Shah", "Hospital Address : 101 First St, Hyderabad India 12345", "Mobile no. : 555-555-1215", " Exp : 12 years", "55"},
            {"Doctor Name: Dr. Gandhi", "Hospital Address : 202 Second St, Chennai India 12345", "Mobile no. : 555-555-1216", " Exp : 8 years", "40"}
           };
    private String[][] indian_dentists = {
            { " Doctor Name: Dr. Patel", "Hospital Address : 123 Main St, Mumbai India 12345", "Mobile no. : 555-555-1212", "Exp : 10 years", "50"},
        {"Doctor Name: Dr. Kumar", "Hospital Address : 456 Park Ave, Delhi India 12345", "Mobile no. : 555-555-1213", "Exp : 5 years", "45"},
        {"Doctor Name: Dr. Singh", "Hospital Address : 789 Elm St, Bangalore India 12345", "Mobile no. : 555-555-1214", "Exp : 15 years", "60"},
        {"Doctor Name: Dr. Shah", "Hospital Address : 101 First St, Hyderabad India 12345", "Mobile no. : 555-555-1215", " Exp : 12 years", "55"},
        {"Doctor Name: Dr. Gandhi", "Hospital Address : 202 Second St, Chennai India 12345", "Mobile no. : 555-555-1216", " Exp : 8 years", "40"}
};
    private String[][]  dietician = {
            { " Doctor Name: Dr. Patel", "Hospital Address : 123 Main St, Mumbai India 12345", "Mobile no. : 555-555-1212", "Exp : 10 years", "50"},
            {"Doctor Name: Dr. Kumar", "Hospital Address : 456 Park Ave, Delhi India 12345", "Mobile no. : 555-555-1213", "Exp : 5 years", "45"},
            {"Doctor Name: Dr. Singh", "Hospital Address : 789 Elm St, Bangalore India 12345", "Mobile no. : 555-555-1214", "Exp : 15 years", "60"},
            {"Doctor Name: Dr. Shah", "Hospital Address : 101 First St, Hyderabad India 12345", "Mobile no. : 555-555-1215", " Exp : 12 years", "55"},
            {"Doctor Name: Dr. Gandhi", "Hospital Address : 202 Second St, Chennai India 12345", "Mobile no. : 555-555-1216", " Exp : 8 years", "40"}
    };

    private String[][] indian_surgeon = {
            { " Doctor Name: Dr. Patel", "Hospital Address : 123 Main St, Mumbai India 12345", "Mobile no. : 555-555-1212", "Exp : 10 years", "50"},
            {"Doctor Name: Dr. Kumar", "Hospital Address : 456 Park Ave, Delhi India 12345", "Mobile no. : 555-555-1213", "Exp : 5 years", "45"},
            {"Doctor Name: Dr. Singh", "Hospital Address : 789 Elm St, Bangalore India 12345", "Mobile no. : 555-555-1214", "Exp : 15 years", "60"},
            {"Doctor Name: Dr. Shah", "Hospital Address : 101 First St, Hyderabad India 12345", "Mobile no. : 555-555-1215", " Exp : 12 years", "55"},
            {"Doctor Name: Dr. Gandhi", "Hospital Address : 202 Second St, Chennai India 12345", "Mobile no. : 555-555-1216", " Exp : 8 years", "40"}
    };

    private String[][] indian_cardiologists = {
            { " Doctor Name: Dr. Patel", "Hospital Address : 123 Main St, Mumbai India 12345", "Mobile no. : 555-555-1212", "Exp : 10 years", "50"},
            {"Doctor Name: Dr. Kumar", "Hospital Address : 456 Park Ave, Delhi India 12345", "Mobile no. : 555-555-1213", "Exp : 5 years", "45"},
            {"Doctor Name: Dr. Singh", "Hospital Address : 789 Elm St, Bangalore India 12345", "Mobile no. : 555-555-1214", "Exp : 15 years", "60"},
            {"Doctor Name: Dr. Shah", "Hospital Address : 101 First St, Hyderabad India 12345", "Mobile no. : 555-555-1215", " Exp : 12 years", "55"},
            {"Doctor Name: Dr. Gandhi", "Hospital Address : 202 Second St, Chennai India 12345", "Mobile no. : 555-555-1216", " Exp : 8 years", "40"}
    };

    TextView td;
    Button btn;
    String[][] doctor_details={};
    ArrayList list=new ArrayList<>();
    SimpleAdapter sa;
    HashMap<String,String> hm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        td=findViewById(R.id.textViewDDTitle);
        btn=findViewById(R.id.button);

        Intent it=getIntent();
        String title=  it.getStringExtra("title");
        td.setText(title);

        if(title.compareTo("Family Physician")==0){
            doctor_details=indian_family;
        }
        else if(title.compareTo("Dietician")==0){
            doctor_details=dietician;
        }
        else if(title.compareTo("Dentist")==0){
            doctor_details=indian_dentists;
        }
        else if(title.compareTo("Surgeon")==0){
            doctor_details=indian_surgeon;
        }
        else{
            doctor_details=indian_cardiologists;
        }

        for(int i=0;i<doctor_details.length;i++){
            hm=new HashMap<String,String>();
            hm.put("line1",doctor_details[i][0]);
            hm.put("line2",doctor_details[i][1]);
            hm.put("line3",doctor_details[i][2]);
            hm.put("line4",doctor_details[i][3]);
            hm.put("line5","Cost :"+doctor_details[i][4]+"/-");
            list.add(hm);
        }
        //important for the showing the details of the doctor in the listview using adapter
        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        ListView lst=findViewById(R.id.listView);
        lst.setAdapter(sa);

       lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Intent it=new Intent(Doctor_Details_Activity.this,Book_Apointment_Activity.class);
               it.putExtra("text1" ,title);
               it.putExtra("text2" ,doctor_details[i][0]);
               it.putExtra("text3" ,doctor_details[i][1]);
               it.putExtra("text4" ,doctor_details[i][3]);
               it.putExtra("text5" ,doctor_details[i][4]);
               startActivity(it);
           }
       });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Doctor_Details_Activity.this,find_Doctor_Activity.class));
            }
        });
    }
}