package com.example.allegrostudia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    private Button categorybtn;
    private Button searchbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        categorybtn = (Button) findViewById(R.id.Category);
        categorybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategoryActivity();
            }});

        searchbtn = (Button) findViewById(R.id.Search);
        searchbtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 openCategoryActivity();
             }

         });
    }
    public void openCategoryActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void openSearchActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
