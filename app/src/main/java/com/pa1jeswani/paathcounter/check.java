package com.pa1jeswani.paathcounter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class check extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        Boolean Registered;
        SharedPreferences sharedPref = getSharedPreferences("reg", Context.MODE_PRIVATE);
        Registered = sharedPref.getBoolean("Registered", false);

        if (!Registered)
        {
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }else {
            startActivity(new Intent(this,UserMain.class));
            finish();
        }
    }
}
