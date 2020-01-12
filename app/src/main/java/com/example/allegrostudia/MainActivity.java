package com.example.allegrostudia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import  android.text.Editable;


public class MainActivity extends AppCompatActivity {
    String TAG = MainActivity.class.getSimpleName();
    EditText etSearchTerms;
    Button btnSearch;
    Button  btnCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {
            Intent intent = getIntent();
            String intentJsonPath = intent.getStringExtra("jsonPath");
            long subID = intent.getLongExtra("lastID", 0);
            String jsonPath = intentJsonPath.replace("*", Long.toString(subID));
            final String fullJsonPath = jsonPath.replace("name", "id");
            etSearchTerms = (EditText) findViewById(R.id.etSearchTerms);
            etSearchTerms.addTextChangedListener(textWatcher);

            btnSearch = (Button) findViewById(R.id.BtnSearch);
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void openStatisticActivity(String btnID, String fullJsonPath, String name){
        Intent intent = new Intent(this,StatisticActivity.class);
        intent.putExtra("ID", btnID);
        intent.putExtra("jsonPath", fullJsonPath);
        intent.putExtra("name", fullJsonPath);
        startActivity(intent);
    }
    private TextWatcher textWatcher = new TextWatcher(){
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count){
            String searchInput = etSearchTerms.getText().toString().trim();

            btnSearch.setEnabled(!searchInput.isEmpty());
        }
        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}