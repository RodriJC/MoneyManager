package com.juancka.capitalmanager.fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.juancka.capitalmanager.R;
import com.juancka.capitalmanager.db.DbHelper;
import com.juancka.capitalmanager.model.Operation;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class mainFragment extends Fragment {

    // Fragment components
    private TextView presentation;
    private EditText baseMoney;
    private Button insertBase;

    // Atributes
    private double initialQuantity = 0;


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
    }

    /**
     * Initialize fragment components
     */
    public void init(){
        // TextView
        this.presentation = getView().findViewById(R.id.main_presentation);
        // EditText
        this.baseMoney = getView().findViewById(R.id.main_baseMoney);
        // Button
        this.insertBase = getView().findViewById(R.id.main_insertBase);
    }

    /**
     * Insert quantity base and generate a first register in a BBDD
     */
    public void inserBase(){

        insertBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!baseMoney.getText().toString().equals("")){
                    initialQuantity = Double.parseDouble(baseMoney.getText().toString());

                    // Database and table creation
                    DbHelper dbHelper = new DbHelper(getActivity());
                    SQLiteDatabase db = dbHelper.getWritableDatabase();

                    // Insert init operation
                    dbHelper.saveTransaction(db, createInitialTransaction(initialQuantity));
                }else{
                    Toast.makeText(getActivity(), "Insert a quantity", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Create the initial transaction
     * @param initialQuantity
     * @return
     */
    private Operation createInitialTransaction(Double initialQuantity){

        Operation operation = new Operation();
        operation.setMoneyUpdate(initialQuantity);
        operation.setActualMoney(initialQuantity);
        operation.setOperation("INITIAL");
        operation.setDetails("Initial money");
        operation.setDate(currentDate());

        return operation;
    }

    /**
     * Generate de current date
     * @return
     */
    private String currentDate(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String date = sdf.format(calendar.getTime());

        return  date;
    }
}