package com.example.allegrostudia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CategoryActivity extends AppCompatActivity {

     String category[] = new String []{"Elektronika", "Dom"};

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , category);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openCategoryActivity();
            }
        });
    }
    private void openCategoryActivity(){
        Intent intent = new Intent(this,SubCategoryActivity.class);
        startActivity(intent);
    }
}
