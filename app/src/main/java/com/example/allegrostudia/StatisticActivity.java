package com.example.allegrostudia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class StatisticActivity extends AppCompatActivity {
    String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        try {

        }catch(Exception ex) {
            Log.e(TAG, ex.getMessage());
        }
    }
}
