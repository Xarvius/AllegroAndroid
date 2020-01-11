package com.example.allegrostudia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SubSubActivity extends AppCompatActivity {

    String subsubcategory[] = new String []{"1 subsubkategoria z wybraneej kategorii", "Kolejna subsubkategoria"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_sub);
        ListView listSubcategoryView = (ListView) findViewById(R.id.listSubSubcategoryView );
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , subsubcategory);
        listSubcategoryView.setAdapter(adapter);
        listSubcategoryView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openSubSubCategoryActivity();
            }
        });

    }
    private void openSubSubCategoryActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
