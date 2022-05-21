package com.example.bitmart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bitmart.model.bid.Bid;
import com.example.bitmart.model.initialvalue.InitialValue;
import com.example.bitmart.retrofitservice.ConnectAPI;
import com.example.bitmart.retrofitservice.RetrofitService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PriceView extends AppCompatActivity {


    String StrBasePrice;
    Double BasePrice;
    public String currency, currName , initVal, id;
    public boolean isNewBid = true;
    String result= "";

    private static HttpURLConnection connection;

    BufferedReader reader;
    String line;
    StringBuffer responseContent = new StringBuffer();

    TextView txtCurrName;
    TextView initPrice, message;
    EditText userPrice;
    Button btnAccept, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_view);

        Intent cInt = getIntent();
        currency = cInt.getStringExtra("currency");
        currName = cInt.getStringExtra("currName");
        id = cInt.getStringExtra("userID");
        userPrice = (EditText)findViewById(R.id.editPrice);
        message = (TextView) findViewById(R.id.message);
        initPrice = (TextView)findViewById(R.id.initPrice);
        txtCurrName = findViewById(R.id.txtCurrName);
        btnAccept = findViewById(R.id.btnAddToBid);
        btnBack = findViewById(R.id.btnBackToList);

        txtCurrName.setText(currName);

        new jsonTask().execute();

        //next Activity
        final Intent nextInt = new Intent(this,ActivityUser.class);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent backInt = new Intent(getApplicationContext(),CurrencyList.class);
                startActivity(backInt);
            }
        });
        RetrofitService retrofitService = new RetrofitService();
        ConnectAPI connectAPI = retrofitService.getRetrofit().create(ConnectAPI.class);

        InitialValue initialValue = new InitialValue();

        initialValue.setSymbol(currency);

        connectAPI.getInitialvalue(initialValue)
                .enqueue(new Callback<InitialValue>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(@NonNull Call<InitialValue> call, @NonNull Response<InitialValue> response) {
                        assert response.body() != null;
                        InitialValue initialValue1 = response.body();
                        if (initialValue1.isHasInitial()) {
                            isNewBid = false;
                            initVal = String.valueOf(initialValue1.getInitialValue());
                            initPrice.setText(initVal);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<InitialValue> call, Throwable t) {

                    }
                });

        btnAccept.setOnClickListener( View-> {

            int ID = Integer.parseInt(id);
            String str1 = initPrice.getText().toString();
            String str2 = userPrice.getText().toString();
            double d1 = Double.parseDouble(str1);
            double d2 = Double.parseDouble(str2);
            Bid bid = new Bid();
            InitialValue initialValue2 = new InitialValue();

            bid.setUserID(ID);
            bid.setSymbol(currency);
            if(isNewBid){
                bid.setAmount(d1);

                initialValue2.setSymbol(currency);
                initialValue2.setInitialValue(d1);
                initialValue2.setHasInitial(true);
            }
            else {
                bid.setAmount(d2);
            }

            if(isNewBid) {
            connectAPI.saveBid(bid)
                    .enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                            String message = response.body();
                            Toast.makeText(PriceView.this, "Successfully place the bid", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {

                        }
                    });

                connectAPI.saveInitVal(initialValue2)
                        .enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                                String message = response.body();
                                Intent x = new Intent(PriceView.this, MainActivity.class);
                                startActivity(x);
                                Toast.makeText(PriceView.this, "Set initial value", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {

                            }
                        });
            }
            else {
                connectAPI.saveBid(bid)
                        .enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                                String message = response.body();
                                Intent x = new Intent(PriceView.this, MainActivity.class);
                                startActivity(x);
                                Toast.makeText(PriceView.this, "Successfully place the bid", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {

                            }
                        });
            }
        });
    }

    class jsonTask extends AsyncTask<Void, Void, String> {

        public String url = "https://min-api.cryptocompare.com/data/price?fsym="+currency+"&tsyms=USD";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL myurl = new URL(url);
                HttpURLConnection urlConnection = (HttpURLConnection)myurl.openConnection();
                InputStreamReader streamReader = new InputStreamReader(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine())!=null){
                    builder.append(line);
                }

                result = builder.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("JsonOB",result);
            return result;
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            JSONArray jsonArray;
            JSONObject object = null;

            Log.e("JsonOBJECT",result);
            try {
                jsonArray = new JSONArray(s);
                for(int i=0;i<jsonArray.length();i++) {
                    object = jsonArray.getJSONObject(i);
                    Log.e("jsonobject",object.toString());
                }

                //txtPrice.setText(object.getString("USD"));
                //txtPrice.setText("HELLO");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            String res = String.valueOf(result);
            Log.e("JsonOBJECTAFTER", s);
            String[] token = res.split(":");
            StrBasePrice = token[1].substring(0,token[1].length()-1);
            if (isNewBid) {
                initPrice.setText(StrBasePrice);
                userPrice.setText(StrBasePrice);
                message.setText("No initial bids. Your price will be the inital bid amount");
            }
        }
    }
}