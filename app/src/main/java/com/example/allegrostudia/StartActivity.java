package com.example.allegrostudia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {


    private Button searchbtn;
    private Button creditbtn;
    private ImageButton exitbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        searchbtn = (Button) findViewById(R.id.Search);
        searchbtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 openSearchActivity();
             }

         });

        creditbtn = (Button) findViewById(R.id.creditBtn);
        creditbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Arkadiusz Strzelec i Damian Piechaczek",Toast.LENGTH_SHORT).show();
            }
        });

        exitbtn = (ImageButton) findViewById(R.id.exitBtn);
        exitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }


    public void openSearchActivity() {
        Intent intent = new Intent(this,CategoryActivity.class);
        startActivity(intent);
    }

}
