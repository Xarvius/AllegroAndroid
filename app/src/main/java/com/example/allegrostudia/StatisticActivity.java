package com.example.allegrostudia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.jayway.jsonpath.JsonPath;

import java.io.IOException;
import java.io.InputStream;

public class StatisticActivity extends AppCompatActivity {
    String TAG = MainActivity.class.getSimpleName();
    Context context;
    SearchTask searchTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {
            String json = loadJSONFromAssets();
            Intent intent = getIntent();
            String btnID = intent.getStringExtra("ID");
            String jsonPath = intent.getStringExtra("jsonPath");
            String productName = intent.getStringExtra("name");
            String categoriesList = JsonPath.read(json, jsonPath);
            if(btnID == "product"){

            }
            searchTask = new SearchTask();

        }catch(Exception ex) {
            Log.e(TAG, ex.getMessage());
        }
    }
<<<<<<< HEAD
    public String loadJSONFromAssets() {
        String json = null;
        try {
            InputStream inputStream = context.getAssets().open("categories.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
=======
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
>>>>>>> 09fe663a54d4af0766529202fe5867d738ef0c2e
    }
}
