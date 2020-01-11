package com.example.allegrostudia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etSearchTerms;
    Button btnSearch;
    TextView tvSearchResults;
    MyLoopjTask myLoopjTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSearchTerms = (EditText) findViewById(R.id.etSearchTerms);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        tvSearchResults = (TextView) findViewById(R.id.tvSearchResults);

        btnSearch.setOnClickListener(this);

        myLoopjTask = new MyLoopjTask();
    }

    @Override
    public void onClick(View v) {
        String searchTerm = etSearchTerms.getText().toString();
        etSearchTerms.setText("");
        // make loopj http call
        myLoopjTask.executeLoopjCall(searchTerm);
    }
}