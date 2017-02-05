package com.ridhwaan.any.tugo;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import  android.support.design.widget.BottomSheetDialogFragment;
/**
 * Created by Ridhwaan on 2/5/17.
 */

public class BottotSheetEditEventFragment extends  BottomSheetDialogFragment{



    private BottomSheetBehavior.BottomSheetCallback mBottomSheetCallback
            = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {

        }
    };




    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        View v = View.inflate(getContext(),R.layout.fragment_bottom_sheet,null);
        dialog.setContentView(v);





    }



}