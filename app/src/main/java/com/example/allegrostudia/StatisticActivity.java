package com.example.allegrostudia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.jayway.jsonpath.JsonPath;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class StatisticActivity extends AppCompatActivity implements OnLoopjCompleted{
    String TAG = MainActivity.class.getSimpleName();
    Context context;
    SearchTask searchTask;
    TextView textMin;
    TextView textMax;
    TextView textAvg;
    TextView textQua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {
            context = this;
            String json = loadJSONFromAssets();
            Intent intent = getIntent();
            String btnID = intent.getStringExtra("ID");
            String jsonPath = intent.getStringExtra("jsonPath");
            String productName = "";
            if(btnID == "product"){
                 productName = intent.getStringExtra("name");
            }
            String categoriesID = JsonPath.read(json, jsonPath);
            searchTask = new SearchTask(productName, categoriesID, this);
            searchTask.executeLoopjCall();

        }catch(Exception ex) {
            Log.e(TAG, ex.getMessage());
        }
    }

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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void taskCompleted(String results) {
        List<String> prices= JsonPath.read(results, "$.items.promoted[*].sellingMode.price.amount");
        List<String> quantity= JsonPath.read(results, "$.items.promoted[*]");
        float avg = 0;
        int i = 0;
        int fullQuantity = 0;
        float min=0;
        float max=0;
        for (String temp : prices){
            i++;
            float number = Float.valueOf(temp);
            avg += number;
            if (i == 1)
                min = number;
            if(min > number)
                min = number;
            if(max < number)
                max = number;
        }
        avg = avg / i;
        fullQuantity = quantity.size();
        textMin = findViewById(R.id.textViewMin);
        textMax = findViewById(R.id.textViewMax);
        textAvg = findViewById(R.id.textViewAvg);
        textQua = findViewById(R.id.textViewQuantity);
        textMin.setText(Float.toString(min));
        textMax.setText(Float.toString(max));
        textAvg.setText(Float.toString(avg));
        textQua.setText(Integer.toString(fullQuantity));
    }
}
