package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LabTestDetailsActivity extends AppCompatActivity {
    TextView textpackageName, tpcost;
    EditText edDetails;
    Button atcbutton,backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);
        textpackageName=findViewById(R.id.textViewLTD);
        tpcost=findViewById(R.id.textViewLTDfee);
        edDetails=findViewById(R.id.editTextmultiline);
        atcbutton=findViewById(R.id.atcbutton);
        backbutton=findViewById(R.id.LTDbackbutton);

        edDetails.setKeyListener(null);

        Intent it=getIntent();
        textpackageName.setText(it.getStringExtra("text1"));
        edDetails.setText(it.getStringExtra("text2"));
        tpcost.setText("Total Cost: " +it.getStringExtra("text3"));

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestDetailsActivity.this,LabTestActivity.class));
            }
        });

        atcbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp= getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sp.getString("username","").toString();
                String product=textpackageName.getText().toString();
                float price=Float.parseFloat(it.getStringExtra("text3").toString());

                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                if(db.checkcard(username,product)==1){
                    Toast.makeText(LabTestDetailsActivity.this, "Product Already Added", Toast.LENGTH_SHORT).show();
                }
                else{
                    db.cardadd(username,product,price,"lab");
                    Toast.makeText(LabTestDetailsActivity.this, "Record added to cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetailsActivity.this,LabTestActivity.class));
                }
            }
        });

    }
}