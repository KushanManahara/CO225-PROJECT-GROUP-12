package com.example.bitmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class StartNewBid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_new_bid);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.ongoingBid);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ongoingBid:
                        startActivity(new Intent(getApplicationContext(), Ongoing.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.scheduledBids:
                        startActivity(new Intent(getApplicationContext(), Scheduled.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.newBid:
                        return true;
                }
                return false;
            }
        });
    }
}