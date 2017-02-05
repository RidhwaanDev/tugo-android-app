package com.ridhwaan.any.tugo;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Ridhwaan on 2/4/17.
 */

public class DatePickerDialog extends DialogFragment {
    public static final String KEY_DATE = "date_id";

    public static final String ARGS_DATE = DatePickerDialog.class.getCanonicalName();

    private Date date;
    private DatePicker mDatePicker;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date,null);

        date = (Date) getArguments().getSerializable(KEY_DATE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);



        mDatePicker = (DatePicker) v.findViewById(R.id.date_picker_dialog);
        mDatePicker.init(year,month,day,null);



        return new AlertDialog.Builder(getActivity())
                .setTitle("Choose Date")
                .setView(v)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        int day = mDatePicker.getDayOfMonth();
                        int month = mDatePicker.getMonth();
                        int year = mDatePicker.getYear();

                        Date date = new GregorianCalendar(year,month,day).getTime();

                        sendData(date,Activity.RESULT_OK);


                    }
                }).setNegativeButton(android.R.string.cancel, null)
                .create();

    }



    public static DatePickerDialog newInstance(Date date){
        Bundle args = new Bundle();
        args.putSerializable(KEY_DATE,date);

        DatePickerDialog datePickerDialog = new DatePickerDialog();
        datePickerDialog.setArguments(args);
        return datePickerDialog;
    }


    public void sendData(Date date, int requestCode){

        Intent i = new Intent();
        i.putExtra(ARGS_DATE,date);


        getTargetFragment().onActivityResult(getTargetRequestCode(),requestCode,i);





    }



}
