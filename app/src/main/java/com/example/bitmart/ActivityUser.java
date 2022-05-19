package com.example.bitmart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityUser extends AppCompatActivity {

    double BasePrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent ActivityUser = getIntent();
        String id = ActivityUser.getStringExtra("userID");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_currency);


        Button btnSelectCurr = findViewById(R.id.btnSelectCurr);
        Button btnAdd = findViewById(R.id.btnAddForBid);
        Button home = findViewById(R.id.home);
        Button back = findViewById(R.id.back);

        btnSelectCurr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CurrencyList.class);
                i.putExtra("userID", id);
                startActivity(i);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), OnGoing.class));
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(ActivityUser.this, MainActivity.class);
                startActivity(register);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(ActivityUser.this, OnGoing.class);
                startActivity(register);
            }
        });
    }

}
