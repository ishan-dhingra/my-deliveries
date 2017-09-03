package com.anythingintellect.mydeliveries;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by ishan.dhingra on 03/09/17.
 */

public class MyDeliveriesApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }

}
