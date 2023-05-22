package com.juancka.capitalmanager.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.juancka.capitalmanager.R;
import com.juancka.capitalmanager.fragments.addFragment;
import com.juancka.capitalmanager.fragments.detailsFragment;
import com.juancka.capitalmanager.fragments.listFragment;
import com.juancka.capitalmanager.model.Operation;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OperationAdapter extends RecyclerView.Adapter<OperationAdapter.OperationAdapterHolder>{

    private ArrayList<Operation> operations;
    private LayoutInflater inflater;
    private Context context;

    public OperationAdapter(LayoutInflater inflater, ArrayList<Operation>operations, Context context){
        this.context = context;
        this.operations = operations;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public OperationAdapter.OperationAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OperationAdapter.OperationAdapterHolder(inflater.inflate(R.layout.list_item_operation,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OperationAdapter.OperationAdapterHolder holder, int position) {
        holder.show(position);
    }

    @Override
    public int getItemCount() {
        return operations.size();
    }


    public class OperationAdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView date, update, type, total, details;
        DecimalFormat df = new DecimalFormat("#.00");


        public OperationAdapterHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.li_Date);
            update = itemView.findViewById(R.id.li_Update);
            type = itemView.findViewById(R.id.li_Type);

            itemView.setOnClickListener(this);
        }

        public void show(int position){
            this.date.setText(operations.get(position).getDate());
            this.update.setText(df.format(operations.get(position).getMoneyUpdate()) + '€');
            this.type.setText(operations.get(position).getOperation());
        }

        private void toDetailsFragment(){
            String date = operations.get(getAdapterPosition()).getDate();
            String update = df.format(operations.get(getAdapterPosition()).getMoneyUpdate()) + '€';
            String type = operations.get(getAdapterPosition()).getOperation();
            String total = df.format(operations.get(getAdapterPosition()).getActualMoney()) + '€';
            String details = operations.get(getAdapterPosition()).getDetails();

            Fragment fragment = detailsFragment.newInstance(date, update, type, total, details);

            FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, fragment)
                    .addToBackStack(null)
                    .commit();

        }
        @Override
        public void onClick(View view) {
            toDetailsFragment();
        }
    }
}