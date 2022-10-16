package com.juancka.capitalmanager.model;

import android.content.ContentValues;

import com.juancka.capitalmanager.db.OperationsContract;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Operation {

    // ATTRIBUTES
    private String date, operation, details;
    private double moneyUpdate, actualMoney;

    // CONSTRUCTOR
    public Operation(){}

    /**
     * Make an operation with the current date
     * @param operation
     * @param moneyUpdate
     * @param actualMoney
     * @param details
     */
    public Operation(String operation, double moneyUpdate, double actualMoney,
                     String details){
        this.date = currentDate();
        this.operation = operation;
        this.moneyUpdate = moneyUpdate;
        this.actualMoney = actualMoney;
        this.details = details;
    }


   /**
     * Make an operation with all the attributos by parameters
     * @param date
     * @param operation
     * @param moneyUpdate
     * @param actualMoney
     * @param details
     */
    public Operation(String date, String operation, double moneyUpdate, double actualMoney,
                     String details){
        this.date = date;
        this.operation = operation;
        this.moneyUpdate = moneyUpdate;
        this.actualMoney = actualMoney;
        this.details = details;
    }

    // GETTER/SETTER
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getMoneyUpdate() {
        return moneyUpdate;
    }

    public void setMoneyUpdate(double moneyUpdate) {
        this.moneyUpdate = moneyUpdate;
    }

    public double getActualMoney() {
        return actualMoney;
    }

    public void setActualMoney(double actualMoney) {
        this.actualMoney = actualMoney;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    // METHODS

    /**
     * Convert the Operation to a ContentValue to save it on DB
     * @return
     */
    public ContentValues toContentValue(){

        ContentValues cv = new ContentValues();
        cv.put(OperationsContract.OperationsEntry.DATE, getDate());
        cv.put(OperationsContract.OperationsEntry.OPERATION, getOperation());
        cv.put(OperationsContract.OperationsEntry.MONEY_UPDATE, getMoneyUpdate());
        cv.put(OperationsContract.OperationsEntry.ACTUAL_MONEY, getActualMoney());
        cv.put(OperationsContract.OperationsEntry.DETAIL, getDetails());

        return cv;
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
