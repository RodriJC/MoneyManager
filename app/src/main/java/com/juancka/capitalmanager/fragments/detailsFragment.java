package com.juancka.capitalmanager.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.juancka.capitalmanager.R;

public class detailsFragment extends Fragment {

    // Fragment components
    private TextView total, update, type, date, details;


    public detailsFragment() {
        // Required empty public constructor
    }

    /**
     * We generate a new instance of the fragment with parameter that we want to show
     * @param date
     * @param update
     * @param type
     * @return
     */
    public static detailsFragment newInstance (String date, String update, String type,
                                               String total, String details) {
        detailsFragment fragment = new detailsFragment();

        // A bundle is a container for parameters, with putString we asign a key/value data
        Bundle args = new Bundle();
        args.putString("date", date);
        args.putString("update", update);
        args.putString("type", type);
        args.putString("total", total);
        args.putString("details", details);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        init();
    }

    // Initialize parameters and bring fragments parameters
    private void init(){
        // GetArguments obtain the parameters of the fragment
        Bundle args = getArguments();
        this.date = getActivity().findViewById(R.id.detail_Date);
        this.total = getActivity().findViewById(R.id.detail_Total);
        this.update = getActivity().findViewById(R.id.detail_Update);
        this.type = getActivity().findViewById(R.id.detail_Type);
        this.details = getActivity().findViewById(R.id.detail_Details);
        if (args != null) {
            this.date.setText(args.getString("date"));
            this.update.setText(args.getString("update"));
            this.type.setText(args.getString("type"));
            this.details.setText(args.getString("details"));
            this.total.setText(args.getString("total"));
        }
    }

}