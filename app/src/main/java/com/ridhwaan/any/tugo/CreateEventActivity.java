package com.ridhwaan.any.tugo;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CreateEventActivity extends SingleFragmentActivity{


    public static final String ARG_EVENT = EventListActivity.class.getCanonicalName();

    @Override
    public Fragment createFragment() {

        return new CreateEventFragment();
    }
    public static Intent newInstance (Context packageContext, EventObject eventObj ){
        Intent i = new Intent(packageContext,CreateEventActivity.class);
        i.putExtra(ARG_EVENT, eventObj);

        return i;



    }
}
