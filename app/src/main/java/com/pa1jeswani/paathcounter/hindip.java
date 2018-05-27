package com.pa1jeswani.paathcounter;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class hindip extends Fragment {

    TextView tvPathgurr;
    Button btnHOnce;


    public hindip() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vv = inflater.inflate(R.layout.fragment_hindip, container, false);
        //tvPathgurr= vv.findViewById(R.id.tvHindi);
        btnHOnce = vv.findViewById(R.id.btnHOnce);

        btnHOnce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity().getApplicationContext(),counterAct.class);
                i.putExtra("count","1");
                startActivity(i);
                getActivity().finish();
            }
        });
        return vv;
        //return inflater.inflate(R.layout.fragment_hindip, container, false);
    }

}
