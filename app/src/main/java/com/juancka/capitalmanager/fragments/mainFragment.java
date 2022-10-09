package com.juancka.capitalmanager.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.juancka.capitalmanager.R;


public class mainFragment extends Fragment {

    // Fragment components
    private TextView titleBase, totalMoney, resultMoney;
    private EditText baseMoney, changedMoney;
    private Button insertBase, restMoney, addMoney;

    // Atributes
    private double actualQuantity = 0;


    public mainFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        init();
        inserBase();
        restMoneyOp();
        addMoneyOp();
    }

    /**
     * Initialize fragment components
     */
    public void init(){
        // TextView
        this.titleBase = getView().findViewById(R.id.main_titleBase);
        this.totalMoney = getView().findViewById(R.id.main_totalMoney);
        this.resultMoney = getView().findViewById(R.id.main_resultMoney);
        // EditText
        this.baseMoney = getView().findViewById(R.id.main_baseMone);
        this.changedMoney = getView().findViewById(R.id.main_changedMoney);
        // Button
        this.insertBase = getView().findViewById(R.id.main_insertBase);
        this.restMoney = getView().findViewById(R.id.main_restMoney);
        this.addMoney = getView().findViewById(R.id.main_addMoney);
    }

    /**
     * Insert quantity base
     */
    public void inserBase(){
        insertBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualQuantity = Double.parseDouble(baseMoney.getText().toString());
                resultMoney.setText(baseMoney.getText().toString());
            }
        });
    }

    /**
     * Rest money
     */
    public void restMoneyOp(){
        restMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualQuantity -= Double.parseDouble(changedMoney.getText().toString());
                resultMoney.setText(String.valueOf(actualQuantity));
            }
        });
    }

    /**
     * Add money
     */
    public void addMoneyOp(){
        addMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualQuantity += Double.parseDouble(changedMoney.getText().toString());
                resultMoney.setText(String.valueOf(actualQuantity));
            }
        });
    }
}