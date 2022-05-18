package com.example.bitmart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        Button adminLogin = findViewById(R.id.adminLoginBtn);
        Button root = findViewById(R.id.root);

        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adminLogin = new Intent(AdminLogin.this, AdminPage.class);
                startActivity(adminLogin);
            }
        });

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adminLogin = new Intent(AdminLogin.this, MainActivity.class);
                startActivity(adminLogin);
            }
        });

    }
}