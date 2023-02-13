package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HealthArticlesDetailsActivity extends AppCompatActivity {
   TextView tv;
   ImageView img;
   Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles_details);

        img=findViewById(R.id.imageView);
        tv=findViewById(R.id.textViewDDTitle);
        btn=findViewById(R.id.buttonOD);

        Intent it=getIntent();
        tv.setText(it.getStringExtra("text1"));

        Bundle bd=getIntent().getExtras();
        if(bd!=null){
            int resId=bd.getInt("text2");
            img.setImageResource(resId);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthArticlesDetailsActivity.this,HealthArticlesActivity.class ));
            }
        });

    }
}