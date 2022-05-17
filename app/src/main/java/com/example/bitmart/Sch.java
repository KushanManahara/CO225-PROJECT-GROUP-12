package com.example.bitmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Sch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sch);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.scheduledBids);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ongoingBid:
                        startActivity(new Intent(getApplicationContext(), On.class));
                        overridePendingTransition(0,0);
                        return true;

//                    case R.id.newBid:
//                        startActivity(new Intent(getApplicationContext(), New.class));
//                        overridePendingTransition(0,0);
//                        return true;

                    case R.id.scheduledBids:
                        return true;
                }
                return false;
            }
        });
    }
}