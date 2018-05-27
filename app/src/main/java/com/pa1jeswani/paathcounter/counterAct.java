package com.pa1jeswani.paathcounter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class counterAct extends AppCompatActivity {

    EditText etSubmit;
    Button btnSubmit;

    TextView tvTotalC, tvMyc, tvgotech, tvpawan;
    int total = 0, myc = 0;
    boolean bt = false;
    String name, no,sintent="n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        SharedPreferences sharedPref = getSharedPreferences("reg", Context.MODE_PRIVATE);
        name = sharedPref.getString("uname", "deafult");
        no = sharedPref.getString("uphno", "1234567980");
        Intent ii =getIntent();
        if(ii.hasExtra("count"))
        {
            Toast.makeText(this, "reading one", Toast.LENGTH_SHORT).show();
            addOne();
        }
        etSubmit = findViewById(R.id.etEnterCount);
        btnSubmit = findViewById(R.id.btnSubmitCount);
        tvMyc = findViewById(R.id.tvMyCount);
        tvTotalC = findViewById(R.id.tvTotalCount);
        tvpawan = findViewById(R.id.tvPawana);
        tvgotech = findViewById(R.id.tvPoweredbya);
        tvTotalC.setText("" + total);
        tvpawan.setText(" "+tvpawan.getText());
        tvpawan.setPaintFlags(tvpawan.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

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
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        if (!netcheck()) {
            tvTotalC.setText("Please Check the Internet");
        } else {
            Task1 t2 = new Task1();
            bt = true;
            t2.execute("http://gotechservices.in/jaap/?name="+"\""+name+"\"+" +
                    "&count=\"0\"&mobile=\""+no+"\"");
        }


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!netcheck()) {
                    Toast.makeText(counterAct.this,
                            "Check your Internet", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(counterAct.this, "Welcome", Toast.LENGTH_SHORT).show();
                    String acc = etSubmit.getText().toString();
                    if (acc.length() == 0) {
                        etSubmit.setError("Please Enter value Below 50");
                        etSubmit.requestFocus();
                        return;
                    } else if (Integer.parseInt(acc) > 50) {
                        etSubmit.setError("Please Enter value Below 50");
                        etSubmit.requestFocus();
                        return;
                    } else {
                        myc += Integer.parseInt(acc);
                        tvMyc.setText(acc);
                        Task1 t1 = new Task1();
                        t1.execute("http://gotechservices.in/jaap/?name=" + "\"" + name + "\"+" +
                                "&count=\"" + acc + "\"&mobile=\"" + no + "\"");
                        btnSubmit.setText("Submited");
                        btnSubmit.setEnabled(false);
                    }
                }
            }
        });
    }

    boolean netcheck()
        {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            if (!(networkInfo != null && networkInfo.isConnected())) {
                return false;
            } else {
                return true;
            }
        }
     public void addOne() {
        if(!netcheck())
        {
            Toast.makeText(this,
                    "Check Inetrnet", Toast.LENGTH_SHORT).show();
        }
        else{
        Task1 t3 = new Task1();
        t3.execute("http://gotechservices.in/jaap/?name="+"\""+name+"\"+" +
                "&count=\"1\"&mobile=\""+no+"\"");
        }
    }

    public class Task1 extends AsyncTask<String, Void, String> {

        String jsonstr = "";
        String line = "";
        String searchResults = "";
        String name = "";

        @Override
        protected String doInBackground(String... params) {
            URL url = null;
            try {
                url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                //check line propeprly
                while ((line = bufferedReader.readLine()) != null) {
                    jsonstr += line + "\n";
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (jsonstr != null) {
                jsonstr= jsonstr.substring(11,jsonstr.length()-1);
                /*try {
                    JSONObject jsonObject = new JSONObject(jsonstr);
                    name=jsonObject.getString("\"name\"");
                    searchResults = jsonObject.getString("globalcount");
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
            }
            return jsonstr;

        }//end of doInBackground

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(bt)
            {
                int selfcountUptated = s.indexOf("selfcount");
                int globalcountUpdated = s.indexOf("globalcount");
                int mobileindex = s.indexOf("mobile");
                String subs = s.substring(selfcountUptated+11,globalcountUpdated-2);
                tvMyc.setText(subs);
                String glob=s.substring(globalcountUpdated+13,s.length()-1);
                tvTotalC.setText(glob);
            }
            else{
                tvTotalC.setText("no net");
            }
        }
    }//end of task1

    public void websiteRedirect(){
        Uri uri = Uri.parse("http://gotechservices.in/");
        Intent intent= new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
}//end of class