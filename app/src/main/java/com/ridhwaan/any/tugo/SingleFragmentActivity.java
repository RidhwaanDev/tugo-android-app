package com.ridhwaan.any.tugo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Ridhwaan on 2/3/17.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {

        public abstract Fragment createFragment();

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_generic_layout);


        FragmentManager fg = getSupportFragmentManager();
        Fragment fragment = fg.findFragmentById(R.id.frame_layout_fragment_generic_id);

        if(fragment == null){
                  fragment = createFragment();
                  fg.beginTransaction()
                    .add(R.id.frame_layout_fragment_generic_id,fragment)
                    .commit();


        }

    }
}
