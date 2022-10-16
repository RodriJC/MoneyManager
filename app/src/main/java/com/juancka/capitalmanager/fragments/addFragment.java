package com.juancka.capitalmanager.fragments;

import android.database.Cursor;
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

public class addFragment extends Fragment {

    // Frament Content
    private TextView title, quantityTitle, detailsTitle;
    private EditText quantity, details;
    private Button addQuantity, removeQuantity;

    // Fragment attributes
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    // Required an empty constructor
    public addFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        init();
        operations();
    }

    /**
     * Initialize fragment components
     */
    public void init(){
        // TextView
        this.title = getActivity().findViewById(R.id.add_title);
        this.quantityTitle = getActivity().findViewById(R.id.add_quantityTitle);
        this.detailsTitle = getActivity().findViewById(R.id.add_detailsTitle);
        // EditText
        this.quantity = getActivity().findViewById(R.id.add_quantity);
        this.details = getActivity().findViewById(R.id.add_details);
        // Button
        this.addQuantity = getActivity().findViewById(R.id.add_addQuantity);
        this.removeQuantity = getActivity().findViewById(R.id.add_removeQuantity);
        // Database
        this.dbHelper = new DbHelper(getActivity());
        this.db = dbHelper.getWritableDatabase();
    }

    /**
     * Initialize the button's actions to save the new operations
     */
    public void operations(){

        addQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertOperation("INCREMENT");
            }
        });

        removeQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertOperation("DECREMENT");
            }
        });
    }

    /**
     * Register a new operation of type INCREMENT/DECREMENT
     * @param type
     */
    private void insertOperation(String type) {

        if(!quantity.getText().toString().equals("")){

            double actualQuantity; // Actual money obtained of a DB
            double modifiedQuantity = Double.parseDouble(quantity.getText().toString());
            double finalQuantity = 0; // Money after operation

            String newDetails = details.getText().toString();

            Cursor lastOperation = dbHelper.getLastOperation(db); // Last operation of the DB

            if(lastOperation.moveToFirst()){ // If exists an operation on the DB (last operation)
                actualQuantity = lastOperation.getDouble(0);

                if(type.equals("INCREMENT")) {
                    finalQuantity = actualQuantity + modifiedQuantity;
                }else if(type.equals("DECREMENT")){
                    finalQuantity = actualQuantity - modifiedQuantity;
                }else{
                    Toast.makeText(getActivity(), "Incorrect type operation",
                            Toast.LENGTH_SHORT).show();
                }
                dbHelper.saveOperation(db, new Operation(type, modifiedQuantity, finalQuantity,
                        newDetails));

            }else{ // If the type is incorrect
                Toast.makeText(getActivity(), "Failed to locate last register",
                        Toast.LENGTH_SHORT).show();
            }
        }else { // If not exist an operation
            Toast.makeText(getActivity(), "Insert a quantity", Toast.LENGTH_SHORT).show();
        }

    }

}