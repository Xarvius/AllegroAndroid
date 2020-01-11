package com.example.allegrostudia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SubCategoryActivity extends AppCompatActivity {

    String subcategory[] = new String []{"1 subkategoria z wybraneej kategorii", "Kolejna subkategoria"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        ListView listSubcategoryView = (ListView) findViewById(R.id.listSubcategoryView );
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , subcategory);
        listSubcategoryView.setAdapter(adapter);
        listSubcategoryView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openSubCategoryActivity();
            }
        });

    }
    private void openSubCategoryActivity(){
        Intent intent = new Intent(this,SubSubActivity.class);
        startActivity(intent);
    }
}
