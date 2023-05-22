package com.juancka.capitalmanager.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.juancka.capitalmanager.R;
import com.juancka.capitalmanager.adapter.OperationAdapter;
import com.juancka.capitalmanager.db.DbHelper;
import com.juancka.capitalmanager.model.Operation;

import java.util.ArrayList;

public class listFragment extends Fragment {

    // Fragment components
    private TextView title;
    private RecyclerView rv;
    public ArrayList<Operation> operations;
    private ConstraintLayout cl;

    // DB
    private DbHelper dbHelper;
    private SQLiteDatabase db;
    private OperationAdapter adapter;

    public listFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        init();
        fillRecycler();
        obtainOperations();
    }

    /**
     * Init aplication
     */
    private void init(){
        this.title = getActivity().findViewById(R.id.oplist_title);
        this.operations = new ArrayList<>();
        this.dbHelper = new DbHelper(getActivity());
        this.db = dbHelper.getWritableDatabase();

    }

    /**
     * Obtain operations
     */
    private void obtainOperations(){
        // Cursor that obtain all the operations
        Cursor queryOperation = dbHelper.getAllOperations();

        // While exists operations
        while(queryOperation.moveToNext()) {
            // Parameters of the next operation
            String date = queryOperation.getString(1);
            String typeOperation = queryOperation.getString(2);
            Double update = queryOperation.getDouble(3);
            Double total = queryOperation.getDouble(4);
            String details = queryOperation.getString(5);

            // Fill the operation
            Operation operation = new Operation();
            operation.setDate(date);
            operation.setOperation(typeOperation);
            operation.setMoneyUpdate(update);
            operation.setDetails(details);
            operation.setActualMoney(total);
            operations.add(operation);
        }

    }

    /**
     * Fill t he recyclerView
     */
    private void fillRecycler(){
        // Layout manager creation
        this.rv = getActivity().findViewById(R.id.oplist_rv);
        // Setting adapter
        LinearLayoutManager l = new LinearLayoutManager(getContext());
        adapter = new OperationAdapter(getLayoutInflater(), operations, getContext());
        this.rv.setLayoutManager(l);
        this.rv.setAdapter(adapter);
    }
}