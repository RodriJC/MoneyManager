package com.juancka.capitalmanager.controller;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.juancka.capitalmanager.R;
import com.juancka.capitalmanager.db.OperationsContract;

public class OperationsCursorAdapter extends CursorAdapter {


    public OperationsCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.list_item_operation, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        /* Interface references
        TextView type = view.findViewById(R.id.li_Type);
        TextView update = view.findViewById(R.id.li_Update);
        TextView date = view.findViewById(R.id.li_Date);

        // Data from database
        String stType = cursor.getString(cursor.getColumnIndex(OperationsContract.OperationsEntry.OPERATION));
        String stUpdate = cursor.getString(cursor.getColumnIndex(OperationsContract.OperationsEntry.MONEY_UPDATE));
        String stDate = cursor.getString(cursor.getColumnIndex(OperationsContract.OperationsEntry.DATE));

        // Asign data
        type.setText(stType);
        update.setText(stUpdate);
        date.setText(stDate);

        */
    }
}
