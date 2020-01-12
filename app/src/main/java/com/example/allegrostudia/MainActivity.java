package com.example.allegrostudia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    String TAG = MainActivity.class.getSimpleName();
    EditText etSearchTerms;
    Button btnSearch;
    Button  btnCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            Intent intent = getIntent();
            String intentJsonPath = intent.getStringExtra("jsonPath");
            long subID = intent.getLongExtra("lastID", 0);
            String jsonPath = intentJsonPath.replace("*", Long.toString(subID));
            final String fullJsonPath = jsonPath.replace("name", "id");
            etSearchTerms = (EditText) findViewById(R.id.etSearchTerms);

            btnSearch = (Button) findViewById(R.id.btnSearch);
            btnCategory = (Button) findViewById(R.id.categoryStatisticBtn);

            btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = etSearchTerms.getText().toString();
                    openStatisticActivity("product", fullJsonPath, name);
                }
            });
            btnCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openStatisticActivity("category", fullJsonPath, "");
                }
            });
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
        }
    }

    private void openStatisticActivity(String btnID, String fullJsonPath, String name){
        Intent intent = new Intent(this,StatisticActivity.class);
        intent.putExtra("ID", btnID);
        intent.putExtra("jsonPath", fullJsonPath);
        intent.putExtra("name", fullJsonPath);
        startActivity(intent);
    }

}