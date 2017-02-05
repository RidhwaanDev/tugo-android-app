package com.ridhwaan.any.tugo;

import android.view.ViewGroup;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Ridhwaan on 2/3/17.
 */

public class EventObject implements Serializable {

    private String mEventName;
    private String mDateCreated;
    private String mLocation;
    private String mCategory;
    private int mDaysRemaining;
    private String mDeadLine;
    private String mLink;
    private boolean mIsTug;
    private UUID mUUID;


    public UUID getmUUID() {
        return mUUID;
    }

    public EventObject(String mEventName,
                       String mDateCreated,
                       int days,
                       boolean mIsTug, String mLink) {

        this.mEventName = mEventName;
        this.mDateCreated = mDateCreated;
        this.mLocation = mLocation;
        this.mCategory = mCategory;
        this.mDaysRemaining = days;
        this.mDeadLine = mDeadLine;
        this.mIsTug = mIsTug;
        this.mLink = mLink;

       mUUID =  UUID.randomUUID();

    }

    public String getmEventName() {
        return mEventName;
    }

    public EventObject(String mEventName){
        this.mEventName = mEventName;


    }

    public String getmLink() {
        return mLink;
    }

    public void setmLink(String mLink) {
        this.mLink = mLink;
    }

    public void setmEventName(String mEventName) {
        this.mEventName = mEventName;
    }

    public String getmDateCreated() {
        return mDateCreated;
    }

    public void setmDateCreated(String mDateCreated) {
        this.mDateCreated = mDateCreated;
    }

    public String getmLocation() {
        return mLocation;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public String getmCategory() {
        return mCategory;
    }

    public void setmCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public int getmDaysRemaining() {
        return mDaysRemaining;
    }

    public void setmTimeRemaining(int daysLeft) {
        this.mDaysRemaining = daysLeft;
    }

    public String getmDeadLine() {
        return mDeadLine;
    }

    public void setmDeadLine(String mDeadLine) {
        this.mDeadLine = mDeadLine;
    }

    public boolean ismIsTug() {
        return mIsTug;
    }

    public void setmIsTug(boolean mIsTug) {
        this.mIsTug = mIsTug;
    }
}
