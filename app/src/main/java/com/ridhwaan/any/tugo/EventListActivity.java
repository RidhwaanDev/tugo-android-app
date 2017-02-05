package com.ridhwaan.any.tugo;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EventListActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new EventListFragment();
    }






}
