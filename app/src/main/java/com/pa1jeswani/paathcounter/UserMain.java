package com.pa1jeswani.paathcounter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class UserMain extends AppCompatActivity {

    Button btnCounter,btnRead;
    TextView tvWelcm;
    TextView tvgotech,tvpawan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        SharedPreferences sharedPref = getSharedPreferences("reg", Context.MODE_PRIVATE);
        String uname = sharedPref.getString("uname","deafult");

        btnRead = findViewById(R.id.btnRead);

        btnCounter = findViewById(R.id.btnCounter);
        tvWelcm =findViewById(R.id.tvWlcm);
        tvpawan=findViewById(R.id.tvPawan);
        tvgotech =findViewById(R.id.tvPoweredby);
        tvWelcm.setText("Welcome \t"+uname);

        tvpawan.setText(" "+tvpawan.getText());
        tvpawan.setPaintFlags(tvpawan.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

        btnCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (!(networkInfo != null && networkInfo.isConnected())) {
                    Toast.makeText(UserMain.this,
                            "Please Check Your Internet", Toast.LENGTH_SHORT).show();
                } else {
                Intent i = new Intent(UserMain.this,counterAct.class);
                startActivity(i);
                }
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserMain.this,readpaath.class);
                startActivity(i);
            }
        });
        tvgotech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                websiteRedirect();
            }
        });
        tvpawan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.linkedin.com/in/pawan-jeswani-62784b125/");
                Intent intent= new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
    }

    public void websiteRedirect(){
        Uri uri = Uri.parse("http://gotechservices.in/");
        Intent intent= new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
}
