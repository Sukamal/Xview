package com.everi.xview.util;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by sukamal on 17/2/17.
 */

public class AppDialog {


    private Dialog dialog;
    public interface PickDateListener {

        /**
         * On date selected.
         */
        void OnDateSelected(String date);

    }

    public static void displayDatePickerDialog(Context context, final View viewDate, final String displyFormat, final PickDateListener pickDateListener){
        Calendar cal = Calendar.getInstance();
        String date = "";

        if(viewDate instanceof TextView){
            date = ((TextView) viewDate).getText().toString().trim();
        }else if(viewDate instanceof EditText){
            date = ((EditText) viewDate).getText().toString().trim();
        }

        if(!Utility.isNullOrBlank(date)){
            Date date1 = Utility.getDateFromString(date,displyFormat/*"mm dd, yyyy"*/);
            cal.setTime(date1);
        }

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context,0, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                String date =  new StringBuilder().append(year).append("-").append(month+1).append("-").append(dayOfMonth).toString();

                if(viewDate instanceof TextView){
                    ((TextView) viewDate).setText(Utility.convertDateFormat(date,"yyyy-MM-dd",displyFormat));
                }else if(viewDate instanceof EditText){
                    ((EditText) viewDate).setText(Utility.convertDateFormat(date,"yyyy-MM-dd",displyFormat));
                }

                if(pickDateListener != null){
                    pickDateListener.OnDateSelected(date);
                }

            }
        },year,month,day);
        datePickerDialog.show();
    }

    public static void showMessageOKCancel(Activity activity, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(activity)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    public static Dialog showProgressDialog(Context context){
        ProgressDialog mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Please wait...");
        mProgressDialog.show();
        return mProgressDialog;
    }
}
