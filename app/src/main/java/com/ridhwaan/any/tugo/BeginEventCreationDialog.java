package com.ridhwaan.any.tugo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Ridhwaan on 2/3/17.
 */

public class BeginEventCreationDialog extends DialogFragment {

    public static final String ARG_TITLE_EVENT = DialogFragment.class.getCanonicalName();
    private EditText mEditEvent;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
            View v = LayoutInflater.from(getActivity()).inflate(R.layout.create_dialog_layout,null);

        mEditEvent =(EditText) v.findViewById(R.id.edit_event);

AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).
              setTitle("Create Event")
              .setView(v)
              .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {

                      String title = mEditEvent.getText().toString().trim();
                      EventObject eventObject = new EventObject(title);

                     //sendData(Activity.RESULT_OK,eventObject);

                      Intent intent = CreateEventActivity.newInstance(getActivity(),eventObject);
                      getTargetFragment().startActivityForResult(intent,EventListFragment.REQUEST_EVENT_FINAL);

                      //Intent intent = CreateEventActivity.newInstance(getActivity(),eventObject);
                      //startActivity(intent);



                  }
              })
              .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                      sendData(Activity.RESULT_OK,null);
                  }
              })
              .create();


        alertDialog.setCanceledOnTouchOutside(false);


        return alertDialog;

    }




    private void sendData(int resultCode,EventObject e ){

        if(getTargetFragment() == null){

            return;
        }

        if(e == null){

            getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,null);
            return;
        }


        Intent i = new Intent();

        i.putExtra(ARG_TITLE_EVENT,e);
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,i);



    }
}
