package com.example.allegrostudia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class StatisticActivity extends AppCompatActivity {
    String TAG = MainActivity.class.getSimpleName();
    SearchTask searchTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        try {
            Intent intent = getIntent();
            String btnID = intent.getStringExtra("ID");
            String jsonPath = intent.getStringExtra("jsonPath");
            String productName = intent.getStringExtra("name");
            searchTask = new SearchTask();

        }catch(Exception ex) {
            Log.e(TAG, ex.getMessage());
        }
    }
}
