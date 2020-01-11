package com.example.allegrostudia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    EditText etSearchTerms;
    Button btnSearch, btnCategory;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSearchTerms = (EditText) findViewById(R.id.etSearchTerms);

        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStatisticActivity();
            }
        });

        btnCategory = (Button) findViewById(R.id.categoryStatisticBtn);
        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStatisticActivity();
            }
        });

    }
    private void openStatisticActivity(){
        Intent intent = new Intent(this,StatisticActivity.class);
        startActivity(intent);
    }

}