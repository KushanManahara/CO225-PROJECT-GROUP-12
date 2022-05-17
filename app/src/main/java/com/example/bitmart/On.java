package com.example.bitmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class On extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.ongoingBid);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ongoingBid:
                        return true;

//                    case R.id.newBid:
//                        startActivity(new Intent(getApplicationContext(), New.class));
//                        overridePendingTransition(0,0);
//                        return true;

                    case R.id.scheduledBids:
                        startActivity(new Intent(getApplicationContext(), Sch.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}