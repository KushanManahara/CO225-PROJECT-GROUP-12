package com.example.bitmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.bitmart.model.authentication.AuthenticationAccept;
import com.example.bitmart.model.authentication.AuthenticationRequest;
import com.example.bitmart.retrofitservice.ConnectAPI;
import com.example.bitmart.retrofitservice.RetrofitService;
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
                        public void onResponse(@NonNull Call<AuthenticationAccept> call, @NonNull Response<AuthenticationAccept> response) {
                            AuthenticationAccept authenticationAccept = response.body();
                            assert authenticationAccept != null;
                            if (authenticationAccept.isAuthorized()) {

                                Intent OnGoing = new Intent(MainActivity.this, OnGoing.class);
                                assert response.body() != null;

                                String id = String.valueOf(authenticationAccept.getUserID());

                                OnGoing.putExtra("id", id);
                                startActivity(OnGoing);

                                Toast.makeText(MainActivity.this, "Sucessfully logged", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(MainActivity.this, "Incorrect Password or Username", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<AuthenticationAccept> call, @NonNull Throwable t) {
                            Toast.makeText(MainActivity.this, "unable to log",Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}