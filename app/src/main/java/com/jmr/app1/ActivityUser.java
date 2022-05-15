package com.jmr.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ActivityUser extends AppCompatActivity {

    double BasePrice =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Button btnBack, btnSelectCurr, btnPrice;
        btnBack = findViewById(R.id.btnBack);
        btnSelectCurr = findViewById(R.id.btnSelectCurr);
        //btnPrice = findViewById(R.id.btnPrice);

        btnSelectCurr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),CurrencyList.class);
                startActivity(i);
            }
        });


        TextView SelectedCurr;
        SelectedCurr = findViewById(R.id.pltxtSelectedCurr);

        TextView CurrPrice;
        CurrPrice = findViewById(R.id.pltxtPrice);

        Intent cInt = getIntent();
        String currName = cInt.getStringExtra("NextCurrName");
        String currency = cInt.getStringExtra("NextCurrency");
        BasePrice = cInt.getDoubleExtra("NextPrice",0);

        //BasePrice = Double.parseDouble(price);

        SelectedCurr.setText(currName);
        CurrPrice.setText(String.valueOf(BasePrice));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

    }

}
