package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuyMedicineBookActivity extends AppCompatActivity {
    EditText edname,edaddress,edpincode,edcontact;
    Button book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);

        edname=findViewById(R.id.editTextUsername);
        edaddress=findViewById(R.id.editTextAddress);
        edcontact=findViewById(R.id.editTextContactNumber);
        edpincode=findViewById(R.id.editTextPincode);
        book=findViewById(R.id.button);

        //for getting the values which passed in the cardlabactivity
        Intent it=getIntent();
        String[] price=it.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date=it.getStringExtra("date");
//        String time=it.getStringExtra("time");

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sp.getString("username","").toString();

                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                db.addOrder(username,edname.getText().toString(),edaddress.getText().toString(),edcontact.getText().toString(),Integer.parseInt(edpincode.getText().toString()),date.toString(),"",Float.parseFloat(price[1].toString()),"medicine");
                db.removecart(username,"medicine");
                Toast.makeText(BuyMedicineBookActivity.this, "Your booking is done successfully!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(BuyMedicineBookActivity.this,HouseActivity.class));
            }
        });
    }
}