package com.jmr.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnUser, btnAdmin;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUser = findViewById(R.id.btnUser);
        btnAdmin = findViewById(R.id.btnAdmin);
        txt = findViewById(R.id.txtSelect);

        btnUser.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent inUser = new Intent(getApplicationContext(),ActivityUser.class);
                startActivity(inUser);
            }
        });

        btnAdmin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent inAdmin = new Intent(getApplicationContext(),ActivityAdmin.class);
                startActivity(inAdmin);
            }
        });

    }
}