package com.example.bitmart.retrofitservice;

import com.example.bitmart.model.auctions.Auction;
import com.example.bitmart.model.authentication.AuthenticationAccept;
import com.example.bitmart.model.authentication.AuthenticationRequest;
import com.example.bitmart.model.bid.Bid;
import com.example.bitmart.model.initialvalue.InitialValue;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ConnectAPI {

    @GET("/auctions/get-on-going")
    Call<Auction> getAllAuctions();

    @POST("/login")
    Call<AuthenticationAccept> login(@Body AuthenticationRequest authenticationRequest);

    @POST("/has-init-val")
    Call<InitialValue> getInitialvalue(@Body InitialValue initialValue);

    @POST("save/bid")
    Call<String> saveBid(@Body Bid bid);

    @POST("save/initial-value")
    Call<String> saveInitVal(@Body InitialValue initialValue);

}
