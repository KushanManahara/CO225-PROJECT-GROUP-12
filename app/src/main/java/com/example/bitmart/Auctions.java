package com.example.bitmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Auctions extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    OngoingFragment ongoingFragment = new OngoingFragment();
    StartNewBidFragment startNewBidFragment = new StartNewBidFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auctions);
        bottomNavigationView = findViewById(R.id.nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, ongoingFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ongoingBid:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, ongoingFragment).commit();
                        return true;

                    case R.id.newBid:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, startNewBidFragment).commit();
                        return true;
                }

                return false;
            }
        });

    }
}