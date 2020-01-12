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

public class SubSubActivity extends AppCompatActivity {
    String TAG = MainActivity.class.getSimpleName();
    Context context;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_sub);
        try {
            context = this;
            String json = loadJSONFromAssets();
            Intent extras = getIntent();
            final long subID = extras.getLongExtra("ID", 20);
            final long prevID = extras.getLongExtra("prevID", 20);
            final String jsonPath = "$.categories[" + prevID +"].subcategories.categories[" + subID +"].subcategories.categories[*].name";
            List<String> categoriesList = JsonPath.read(json, jsonPath);
            String categories[] = categoriesList.toArray(new String [categoriesList.size()]);
            listView = (ListView) findViewById(R.id.listView);
            ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , categories);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    openSubSubCategoryActivity(id, jsonPath);
                }
            });
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
        }
    }
    private void openSubSubCategoryActivity(long id, String jsonPath){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("lastID", id);
        intent.putExtra("jsonPath", jsonPath);
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
