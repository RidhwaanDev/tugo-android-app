package com.ridhwaan.any.tugo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.CircularArray;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.SimpleFormatter;


public class CreateEventFragment extends Fragment {

    public static final String ARGS_DATE_PICKER = CreateEventFragment.class.getCanonicalName();
    public static final String DIALOG_ID = "1";
    public static final String RESULT_ID = "2";
    private EditText mEditLink;


    private static final int REQUEST_CODE = 1;


    private Date date;


    private Button mSetDateButton;
    private TextView mDateTextView;
    private EditText mTitleEditText;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_event,container,false);



        setHasOptionsMenu(true);


        mDateTextView = (TextView) v.findViewById(R.id.date_text);
        mTitleEditText = (EditText) v.findViewById(R.id.title_event);
        mEditLink = (EditText)v.findViewById(R.id.edit_link);

            Bundle args =  getActivity().getIntent().getExtras();
            EventObject e =(EventObject) args.getSerializable(CreateEventActivity.ARG_EVENT);

        mTitleEditText.setText(e.getmEventName().toString().trim());





        mSetDateButton = (Button) v.findViewById(R.id.set_date_id);

        mSetDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDateDialog();
            }
        });

        return v;
    }


    public void createDateDialog(){
        Date date = Utils.getCurrentDate();

        FragmentManager fg = getFragmentManager();
        DatePickerDialog datePicker = DatePickerDialog.newInstance(date);
        datePicker.setTargetFragment(CreateEventFragment.this,REQUEST_CODE);
        datePicker.show(fg,DIALOG_ID);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if( resultCode != Activity.RESULT_OK){
            return;
        }

        if(requestCode == REQUEST_CODE){
            this.date = (Date) data.getSerializableExtra(DatePickerDialog.ARGS_DATE);

            mDateTextView.setText(date.toString());


        }


    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_event_menu,menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_item_new_post){


            Log.d("TAG", "      MENU ITEM PRESSED");
            Intent i = new Intent();
            i.putExtra(RESULT_ID,getFinalizedEventObject());

            getActivity().setResult(Activity.RESULT_OK,i);
            getActivity().finish();



            return true;


        }

        return false;


    }



    private EventObject getFinalizedEventObject(){
        //Event Name
        String eventName = mTitleEditText.getText().toString().trim();
        if(eventName.isEmpty()){
            eventName = "Event";
        }
        //Event link
        String link = mEditLink.getText().toString().trim().toLowerCase();


        //Event dateCreated
        Calendar calendar = Calendar.getInstance();
        Date date = Utils.getCurrentDate();
        String currentDate = new SimpleDateFormat("EEE, MMM d, ''yy").format(date);
        int startOfEventDay = calendar.get(Calendar.DAY_OF_YEAR);

        //Event Days Left
        int daysLeft;
        int endOfEventDay;
        if(this.date != null) {
            calendar.setTime(this.date);
            endOfEventDay = calendar.get(Calendar.DAY_OF_YEAR);

        } else {calendar.setTime(new Date());}

             endOfEventDay = calendar.get(Calendar.DAY_OF_YEAR);
        Log.d("TAG", "DAY OF YEAR OF EVENT  " + endOfEventDay);
            calendar.setTime(date);

        Log.d("TAG", "DAY OF YEAR OF START EVENT   " + startOfEventDay);

            daysLeft = endOfEventDay - startOfEventDay;
            Log.d("TAG", "DAYS LEFT   "  + daysLeft);


        //Event isTug =

        //Event link




        EventObject eventObject = new EventObject(eventName,currentDate,daysLeft,false,link);
        return eventObject;
    }



}
