package com.example.bitmart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        Button startNewAuction = findViewById(R.id.newAuction);
        Button stopAuction = findViewById(R.id.stopAuction);
        Button home = findViewById(R.id.home);

        startNewAuction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(AdminPage.this, AdminStartNewAuction.class);
                startActivity(login);
            }
        });

        stopAuction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(AdminPage.this, AdminStopAuction.class);
                startActivity(login);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(AdminPage.this, AdminLogin.class);
                startActivity(login);
            }
        });
    }
}