package com.pa1jeswani.paathcounter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.solver.widgets.ConstraintTableLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText etPhone,etnm;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etPhone= findViewById(R.id.etId);
        final SharedPreferences sharedPref = getSharedPreferences("reg", Context.MODE_PRIVATE);
        etnm = findViewById(R.id.etName);
        btnLogin = findViewById(R.id.btnULogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etnm.getText().toString().trim();
                String no =etPhone.getText().toString().trim();
                if(no.length()!=10)
                {
                    etPhone.setError("Invalid Phone No.");
                    etPhone.requestFocus();
                    return;
                }
                else if(name.length()<3)
                {
                    etnm.setError("Not a valid Name");
                    etnm.requestFocus();
                    return;
                }
                else{
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("Registered", true);
                editor.putString("uname", name);
                editor.putString("uphno", no);
                editor.apply();
                Intent i = new Intent(MainActivity.this,UserMain.class);
                /*
                i.putExtra("uname",name);
                i.putExtra("uphno",no);*/
                startActivity(i);
                finish();
                }
            }
        });
    }
}
