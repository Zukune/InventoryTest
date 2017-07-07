package com.mangaschool_app.testfirebase;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by shins on 7/6/2017.
 */

public class Cache extends Application{


    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

    }
}
