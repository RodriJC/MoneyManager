package com.juancka.capitalmanager.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.juancka.capitalmanager.R;
import com.juancka.capitalmanager.db.DbHelper;
import com.juancka.capitalmanager.model.Operation;

import java.text.DecimalFormat;
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
        // Cursosr that obtain all the operations
        DecimalFormat df = new DecimalFormat("#.00");
        Cursor queryOperation = dbHelper.getAllOperations();

        // While exists operations
        while(queryOperation.moveToNext()) {
            // Parameters of the next operation
            String date = queryOperation.getString(1);
            String typeOperation = queryOperation.getString(2);
            Double total = queryOperation.getDouble(4);

            // Fill the operation
            Operation operation = new Operation();
            operation.setDate(date);
            operation.setOperation(typeOperation);
            operation.setActualMoney(Double.parseDouble(df.format(total)));

            operations.add(operation);
        }

    }

    //
    private void fillRecycler(){
        // Layout manager creation
        this.rv = getActivity().findViewById(R.id.oplist_rv);
        // Setting adapter
        LinearLayoutManager l = new LinearLayoutManager(getContext());
        adapter = new OperationAdapter();
        this.rv.setLayoutManager(l);
        this.rv.setAdapter(adapter);
    }


    private class OperationAdapter extends RecyclerView.Adapter<OperationAdapter.OperationAdapterHolder>{

        @NonNull
        @Override
        public OperationAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new OperationAdapterHolder(getLayoutInflater().inflate(R.layout.list_item_operation,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull OperationAdapterHolder holder, int position) {
            holder.show(position);
        }

        @Override
        public int getItemCount() {
            return operations.size();
        }


        public class OperationAdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            TextView date, update, type;

            public OperationAdapterHolder(@NonNull View itemView) {
                super(itemView);
                date = itemView.findViewById(R.id.li_Date);
                update = itemView.findViewById(R.id.li_Update);
                type = itemView.findViewById(R.id.li_Type);
                itemView.setOnClickListener(this);
            }

            public void show(int position){
                this.date.setText(operations.get(position).getDate());
                this.update.setText(String.valueOf(operations.get(position).getActualMoney()) + '€');
                this.type.setText(operations.get(position).getOperation());
            }

            @Override
            public void onClick(View view) {

            }
        }
    }
}