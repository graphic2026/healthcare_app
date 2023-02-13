package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText username, pwd, email, cpwd;
    Button btn;
    TextView tabn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.editTextUsername);
        email = findViewById(R.id.editTextEmail);
        pwd = findViewById(R.id.editTextPassword);
        cpwd = findViewById(R.id.editTextConfirmPassword);
        btn = findViewById(R.id.button);
        tabn = findViewById(R.id.textView3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String us = username.getText().toString();
                String em = email.getText().toString();
                String ps = pwd.getText().toString();
                String cps = cpwd.getText().toString();
                // creating or define the database and passing the all the parameter that given in the database java class
                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                if (us.length() == 0 || em.length() == 0 || ps.length() == 0 || cps.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "fill the full details", Toast.LENGTH_SHORT).show();
                } else {
                    if(ps.compareTo(cps)==0){
                        if (isValid(ps)) {
                            if(isValidE(em)){
                                //insert the value in the database
                                int f=db.register(us,em,ps);
                                 if(f==1){
                                     Toast.makeText(RegisterActivity.this, "Enter unique username", Toast.LENGTH_SHORT).show();

                                 }
                                 else {
                                     Toast.makeText(RegisterActivity.this, "Record Inserted!", Toast.LENGTH_SHORT).show();
                                 }
                            }
                            else{
                                Toast.makeText(RegisterActivity.this, "Email is not valid", Toast.LENGTH_SHORT).show();
                            }


                        } else {
                            Toast.makeText(RegisterActivity.this, "Password must contain atleast one character,one digit,one symbol", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(RegisterActivity.this, "Password is not match with confirm password", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });

        tabn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

    }


    public static boolean isValid(String password) {

        // for checking if password length
        // is between 8 and 15
        if (!((password.length() >= 8)
                && (password.length() <= 15))) {
            return false;
        }

        // to check space
        if (password.contains(" ")) {
            return false;
        }
        if (true) {
            int count = 0;

            // check digits from 0 to 9
            for (int i = 0; i <= 9; i++) {

                // to convert int to string
                String str1 = Integer.toString(i);

                if (password.contains(str1)) {
                    count = 1;
                }
            }
            if (count == 0) {
                return false;
            }
        }

        // for special characters
        if (!(password.contains("@") || password.contains("#")
                || password.contains("!") || password.contains("~")
                || password.contains("$") || password.contains("%")
                || password.contains("^") || password.contains("&")
                || password.contains("*") || password.contains("(")
                || password.contains(")") || password.contains("-")
                || password.contains("+") || password.contains("/")
                || password.contains(":") || password.contains(".")
                || password.contains(", ") || password.contains("<")
                || password.contains(">") || password.contains("?")
                || password.contains("|"))) {
            return false;
        }

        if (true) {
            int count = 0;

            // checking capital letters
            for (int i = 65; i <= 90; i++) {

                // type casting
                char c = (char) i;

                String str1 = Character.toString(c);
                if (password.contains(str1)) {
                    count = 1;
                }
            }
            if (count == 0) {
                return false;
            }
        }

        if (true) {
            int count = 0;

            // checking small letters
            for (int i = 97; i <= 122; i++) {

                // type casting
                char c = (char) i;
                String str1 = Character.toString(c);

                if (password.contains(str1)) {
                    count = 1;
                }
            }
            if (count == 0) {
                return false;
            }
        }

        // if all conditions fails
        return true;
    }
    public static boolean isValidE(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}