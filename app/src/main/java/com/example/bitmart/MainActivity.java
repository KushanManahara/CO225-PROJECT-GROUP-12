package com.example.bitmart;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bitmart.model.authentication.AuthenticationAccept;
import com.example.bitmart.model.authentication.AuthenticationRequest;
import com.example.bitmart.retrofitservice.ConnectAPI;
import com.example.bitmart.retrofitservice.RetrofitService;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button registerBtn = findViewById(R.id.registerBtn);
        Button loginBtn = findViewById(R.id.loginBtn);
        Button adminBtn = findViewById(R.id.loginAdmin);

         EditText inputEditTextEmail = findViewById(R.id.editTextTextEmailAddress);
         EditText inputEditTextPassword = findViewById(R.id.editTextTextPassword);

        RetrofitService retrofitService = new RetrofitService();
        ConnectAPI connectAPI = retrofitService.getRetrofit().create(ConnectAPI.class);

        loginBtn.setOnClickListener(view-> {
            String email = String.valueOf(inputEditTextEmail.getText());
            String password = String.valueOf(inputEditTextPassword.getText());

            AuthenticationRequest authenticationRequest = new AuthenticationRequest();

            authenticationRequest.setEmail(email);
            authenticationRequest.setPassword(password);

            connectAPI.login(authenticationRequest)
                    .enqueue(new Callback<AuthenticationAccept>() {
                        @Override
                        public void onResponse(Call<AuthenticationAccept> call, Response<AuthenticationAccept> response) {
//                            AuthenticationAccept authenticationAccept = response.body();
//                            if (authenticationAccept.isAuthorized()) {
                            Intent login = new Intent(MainActivity.this, OnGoing.class);
                            startActivity(login);
//                                Toast.makeText(MainActivity.this, "Successfully logged", Toast.LENGTH_SHORT).show();
//                            }else{
//                                Toast.makeText(MainActivity.this, "Incorect Password or Username", Toast.LENGTH_SHORT).show();
//                            }
                        }

                        @Override
                        public void onFailure(Call<AuthenticationAccept> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "unable to log",Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}