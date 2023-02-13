package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    //creting the object of the component in the xml
    EditText username,pwd;
    Button btn;
    TextView tabn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=findViewById(R.id.editTextUsername);
        pwd=findViewById(R.id.editTextPassword);
        btn=findViewById(R.id.button);
        tabn=findViewById(R.id.textView3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usrn=username.getText().toString();
                String pd=pwd.getText().toString();
                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                if(usrn.length()==0||pd.length()==0){
                    Toast.makeText(LoginActivity.this, "fill all the details", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(db.login(usrn,pd)==1){
                        //doubt why we use sharedPreference here
                        SharedPreferences sp=getSharedPreferences("MyPref",MODE_PRIVATE);
                        SharedPreferences.Editor ed=sp.edit();
                        ed.putString("username",usrn);
                        ed.apply();
                        startActivity(new Intent(LoginActivity.this,HouseActivity.class));
                        Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Username and password is invalid", Toast.LENGTH_SHORT).show();
                    }
                    }
//
            }
        });

        tabn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }
}