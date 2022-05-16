package com.jmr.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CurrencyList extends AppCompatActivity {


    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_list);

        String[] cList = null;
        //reading CSV
        ArrayList<String> cSymArrList = new ArrayList<String>();
        ArrayList<String> cNameArrList = new ArrayList<String>();


        int count = 0;
        InputStream path = getResources().openRawResource(R.raw.cryptocurrency);
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(path, Charset.forName("UTF-8")));
        String line = "";
        try{
            while((line=bufferedReader.readLine())!=null){
                if(count==0){
                    count++;
                    continue;
                }
                else {
                    String[] tokens = line.split(",");
                    cSymArrList.add(tokens[0]);
                    cNameArrList.add(tokens[1]);
                    count++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //String[] cSymList = new String[] {"BTC","ETH","XRP","BCH","LTC","ADA"};
        //String[] cNameList = new String[] {"Bitcoin","Etherum","Ripple","Bitcoin Cash","Litecoin","Cardano"};
        final Intent cInt = new Intent(this, PriceView.class);


        ListAdapter listAdapter = new ArrayAdapter<String>(this, R.layout.single_row, cNameArrList);
        listView = findViewById(R.id.cListView);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //String item = String.valueOf(adapterView.getItemAtPosition(i));
                String item = cSymArrList.get(i);
                String cName = cNameArrList.get(i);
                cInt.putExtra("currency",item);
                cInt.putExtra("currName",cName);

                startActivity(cInt);
            }
        });

    }

}