package com.jmr.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PriceView extends AppCompatActivity {


    String StrBasePrice;
    Double BasePrice;
    public String currency, currName;
    String result= "";
    //public String url = "https://min-api.cryptocompare.com/data/price?fsym="+"BTC"+"&tsyms=USD";

    private static HttpURLConnection connection;

    BufferedReader reader;
    String line;
    StringBuffer responseContent = new StringBuffer();

    TextView txtPrice, txtCurrName;
    Button btnAccept, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_view);

        Intent cInt = getIntent();
        currency = cInt.getStringExtra("currency");
        currName = cInt.getStringExtra("currName");
        txtPrice = (TextView) findViewById(R.id.txtPrice);
        txtCurrName = findViewById(R.id.txtCurrName);
        btnAccept = findViewById(R.id.btnAddToBid);
        btnBack = findViewById(R.id.btnBackToList);

        txtCurrName.setText(currName);

        new jsonTask().execute();

        //next Activity
        final Intent nextInt = new Intent(this,ActivityUser.class);

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BasePrice = Double.parseDouble(StrBasePrice);
                nextInt.putExtra("NextCurrName",currName);
                nextInt.putExtra("NextCurrency",currency);
                nextInt.putExtra("NextPrice", BasePrice);
                startActivity(nextInt);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent backInt = new Intent(getApplicationContext(),CurrencyList.class);
                startActivity(backInt);
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
                //Log.e("Json",builder.toString());
                //urlConnection.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("JsonOB",result);
            return result;
        }

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
            //StrBasePrice = new StringBuffer(token[1]);
            //StrBasePrice.deleteCharAt(StrBasePrice.length()-1);
            //String str = token2[0];
            txtPrice.setText(StrBasePrice);
        }
    }
}