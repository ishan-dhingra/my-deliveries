package com.anythingintellect.mydeliveries;

import android.app.Application;

import com.anythingintellect.mydeliveries.di.AppComponent;
import com.anythingintellect.mydeliveries.di.BaseModule;
import com.anythingintellect.mydeliveries.di.DBModule;
import com.anythingintellect.mydeliveries.di.DaggerAppComponent;
import com.anythingintellect.mydeliveries.di.NetworkModule;
import com.anythingintellect.mydeliveries.util.Constants;

import io.realm.Realm;

/**
 * Created by ishan.dhingra on 03/09/17.
 */

public class MyDeliveriesApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        initComponent();
    }

    public void initComponent() {
        appComponent = DaggerAppComponent.builder()
                .baseModule(new BaseModule(this))
                .dBModule(new DBModule(false))
                .networkModule(new NetworkModule(Constants.BASE_URL))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
