package com.pa1jeswani.paathcounter;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link gurm#newInstance} factory method to
 * create an instance of this fragment.
 */
public class gurm extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";
    TextView tvPathgur;
    Button btnSOnce;

    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;


    public gurm() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment gurm.
     */
    // TODO: Rename and change types and number of parameters
    public static gurm newInstance(int ins) {
        gurm fragment = new gurm();
        Bundle args = new Bundle();
        args.putInt("args",ins);
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vw= inflater.inflate(R.layout.fragment_gurm, container, false);
        tvPathgur=vw.findViewById(R.id.tvGurmukhi);
        btnSOnce=vw.findViewById(R.id.btnGOnce);
        btnSOnce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity().getApplicationContext(),counterAct.class);
                i.putExtra("count","1");
                startActivity(i);
                getActivity().finish();
            }
        });
        return vw;
    }

}
