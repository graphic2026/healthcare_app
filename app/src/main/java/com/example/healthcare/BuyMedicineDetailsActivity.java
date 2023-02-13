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

public class BuyMedicineDetailsActivity extends AppCompatActivity {
    TextView packagename,Totalprice;
    EditText edDetails;
    Button back,gtcbtn;
//
//
//
//
      @Override
  protected void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
setContentView(R.layout.activity_buy_medicine_details);

        packagename=findViewById(R.id.textViewPCN);
        Totalprice=findViewById(R.id.textViewBMDfee);
        edDetails=findViewById(R.id.editTextmultiline);
        edDetails.setKeyListener(null);
         back=findViewById(R.id.backbutton);
         gtcbtn=findViewById(R.id.atcbutton);


         Intent intent=getIntent();
         packagename.setText(intent.getStringExtra("text1"));
         Totalprice.setText("Total Cost: "+intent.getStringExtra("text3")+"/-");
         edDetails.setText(intent.getStringExtra("text2"));


         back.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(new Intent(BuyMedicineDetailsActivity.this,BuyMedicineActivity.class));
             }
         });

         gtcbtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 SharedPreferences sp=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                 String username=sp.getString("username","").toString();
                 String product=packagename.getText().toString();
                 float price=Float.parseFloat(intent.getStringExtra("text3").toString());

                 Database db=new Database(getApplicationContext(),"healthcare",null,1);

                 if(db.checkcard(username,product)==1){
                     Toast.makeText(BuyMedicineDetailsActivity.this, "Product Already Added", Toast.LENGTH_SHORT).show();

                 }
                 else{
                     db.cardadd(username,product,price,"medicine");
                     Toast.makeText(BuyMedicineDetailsActivity.this, "Record Inserted to Cart", Toast.LENGTH_SHORT).show();
                     startActivity(new Intent(BuyMedicineDetailsActivity.this,BuyMedicineActivity.class));
                 }

             }
         });


    }
}