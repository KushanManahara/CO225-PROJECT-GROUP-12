package com.example.bitmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bitmart.model.auctions.Auction;
import com.example.bitmart.model.authentication.AuthenticationAccept;
import com.example.bitmart.model.authentication.AuthenticationRequest;
import com.example.bitmart.retrofitservice.ConnectAPI;
import com.example.bitmart.retrofitservice.RetrofitService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnGoing extends AppCompatActivity {

    Button btnOpenAuction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongoing);

        btnOpenAuction = findViewById(R.id.btnOpenAuction);

        btnOpenAuction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ActivityUser.class));
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.ongoingBid);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ongoingBid:
                        return true;

                    case R.id.scheduledBids:
                        startActivity(new Intent(getApplicationContext(), Scheduled.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        TextView startTimeView = (TextView)findViewById(R.id.startTimeView);
        TextView endTimeView = (TextView)findViewById(R.id.endTimeView);
        EditText startTime = (EditText) findViewById(R.id.startTime);
        EditText endTime = (EditText) findViewById(R.id.endTime);

        RetrofitService retrofitService = new RetrofitService();
        ConnectAPI connectAPI = retrofitService.getRetrofit().create(ConnectAPI.class);


        connectAPI.getAllAuctions()
                .enqueue(new Callback<Auction>() {
                    @Override
                    public void onResponse(Call<Auction> call, Response<Auction> response) {
                        Auction auction = response.body();
                        if (auction.getAuctionStatus().equals("OnGoing")){
                            startTime.setText(String.valueOf(auction.getStartTime()));
                            endTime.setText(String.valueOf(auction.getEndTime()));
                        } else {
                            startTimeView.setText("");
                            startTime.setText("");
                            endTimeView.setText("No ongoing Auctions");
                            endTime.setText("");
                        }
                    }

                    @Override
                    public void onFailure(Call<Auction> call, Throwable t) {

                    }
                });
    }


}