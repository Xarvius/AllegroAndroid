package com.example.allegrostudia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.jayway.jsonpath.JsonPath;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SubCategoryActivity extends AppCompatActivity {
    String TAG = MainActivity.class.getSimpleName();
    Context context;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        try {
            Intent extras = getIntent();
            final long subID = extras.getLongExtra("ID", 0);
            ArrayAdapter<String> adapter= extractFromJson(subID);
            listView = findViewById(R.id.listView);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    openSubCategoryActivity(id, subID);
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
    private void openSubCategoryActivity(long id, long subID){
        Intent intent = new Intent(this,SubSubActivity.class);
        intent.putExtra("ID", id);
        intent.putExtra("prevID", subID);
        startActivity(intent);
    }
    public ArrayAdapter extractFromJson(long subID){
        context = this;
        String json = loadJSONFromAssets();
        List<String> categoriesList = JsonPath.read(json, "$.categories[" + subID +"].subcategories.categories[*].name");
        String categories[] = categoriesList.toArray(new String [categoriesList.size()]);
        return new ArrayAdapter<String>(this, R.layout.whitecolorlistlayout , categories);
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
}
