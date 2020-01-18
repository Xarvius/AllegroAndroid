package com.example.allegrostudia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.jayway.jsonpath.JsonPath;


public class StartActivity extends AppCompatActivity implements OnLoopjCompleted{

    Context context;
    private Button searchbtn;
    private Button creditbtn;
    private ImageButton exitbtn;
    Intent intent;
    GetAccesToken GetAccesToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        try{
            getToken();
        }catch(Exception ex) {
            Toast.makeText(getBaseContext(), "Please restart app or try later.", Toast.LENGTH_SHORT).show();
        }
        searchbtn = findViewById(R.id.Search);
        searchbtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 openSearchActivity();
             }

         });
        creditbtn = findViewById(R.id.creditBtn);
        creditbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Arkadiusz Strzelec i Damian Piechaczek",Toast.LENGTH_SHORT).show();
            }
        });
        exitbtn = findViewById(R.id.exitBtn);
        exitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }

    public void openSearchActivity() {
        intent = new Intent(this,CategoryActivity.class);
        startActivity(intent);
    }
    public void getToken() {
        context = this;
        GetAccesToken = new GetAccesToken(this);
        GetAccesToken.executeLoopjCall();
    }
    public void taskCompleted(String results) {
          String accessToken = JsonPath.read(results, "$.access_token");
          SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
          SharedPreferences.Editor editor = pref.edit();
          editor.putString("accessToken", accessToken);
          editor.commit();
    }
}
