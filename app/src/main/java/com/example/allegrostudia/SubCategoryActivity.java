package com.example.allegrostudia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        try {
            context = this;
            String json = loadJSONFromAssets();
            Intent extras = getIntent();
            final long subID = extras.getLongExtra("ID", 0);
            List<String> categoriesList = JsonPath.read(json, "$.categories[" + subID +"].subcategories.categories[*].name");

            String categories[] = categoriesList.toArray(new String [categoriesList.size()]);
            listView = (ListView) findViewById(R.id.listView);
            ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , categories);
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
    private void openSubCategoryActivity(long id, long subID){
        Intent intent = new Intent(this,SubSubActivity.class);
        intent.putExtra("ID", id);
        intent.putExtra("prevID", subID);
        startActivity(intent);
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
