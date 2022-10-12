package com.juancka.capitalmanager.db;

import android.provider.BaseColumns;

/**
 * Clase to create the table Operation
 */
public class OperationsContract {

    public static abstract class OperationsEntry implements BaseColumns{
        public static final String TABLE_NAME = "t_operations";
        public static final String ID = "id";
        public static final String DATE = "date";
        public static final String OPERATION = "operation";
        public static final String MONEY_UPDATE = "money_update";
        public static final String ACTUAL_MONEY = "total_money";
        public static final String DETAIL = "detail";

    }
}
