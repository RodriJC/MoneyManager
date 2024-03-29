package com.juancka.capitalmanager.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
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


public class initFragment extends Fragment {

    // Fragment components
    private TextView presentation;
    private EditText baseMoney;
    private Button insertBase;

    // Attributes
    private double initialQuantity = 0;
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public initFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_init, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        init();
        initBD();
        insertBase();
    }

    /**
     * Initialize fragment components
     */
    public void init(){
        // TextView
        this.presentation = getView().findViewById(R.id.init_presentation);
        // EditText
        this.baseMoney = getView().findViewById(R.id.init_baseMoney);
        // Button
        this.insertBase = getView().findViewById(R.id.init_insertBase);

        Log.d("InitFragment","[init]Componentes inicializados");
    }

    public void initBD(){
        this.dbHelper = new DbHelper(getActivity());
        Log.d("InitFragment","[initBD]Creando dbHelper");
        this.db = dbHelper.getWritableDatabase();
        Log.d("InitFragment","[initBD]Creando instancia de base de datos");
        Cursor lastOperation = dbHelper.getLastOperation(db); // Last operation of the DB
        Log.d("InitFragment","[initBD]Obteniendo el ultimo registro");
        if(lastOperation.moveToFirst()){
            Log.d("InitFragment","[initBD]Situamos el cursos en el primer registro si hay valor en la tabla");
            toAddFragment();
        }
    }
    /**
     * Insert quantity base and generate a first register in a BBDD
     */
    public void insertBase(){

        insertBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!baseMoney.getText().toString().equals("")){
                    initialQuantity = Double.parseDouble(baseMoney.getText().toString());
                    // Insert init operation
                    dbHelper.saveOperation(db, createInitialTransaction(initialQuantity));
                    Log.d("InitFragment","[insertBase]Insertamos el registro inicial");
                    // Next fragment
                    toAddFragment();
                }else{
                    Log.d("InitFragment","[insertBase]Error al introducir cantidad");
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
        Log.d("InitFragment","[createInitialTransaction]Creamos la transaccion inicial");
        return new Operation("INITIAL", initialQuantity, initialQuantity,
                "Initial money");

    }

    private void toAddFragment(){
        Log.d("InitFragment","[toAddFragment]Nos vamos al fragmento de anadir");
        Fragment fragment = new addFragment();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, fragment).commit();
    }
}