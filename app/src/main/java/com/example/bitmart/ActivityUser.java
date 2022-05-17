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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Button btnSelectCurr, btnAdd;
        btnSelectCurr = findViewById(R.id.btnSelectCurr);
        btnAdd = findViewById(R.id.btnAddForBid);

        btnSelectCurr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),CurrencyList.class);
                startActivity(i);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),On.class));
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

    }

}
