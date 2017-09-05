package com.anythingintellect.mydeliveries.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anythingintellect.mydeliveries.MyDeliveriesApp;
import com.anythingintellect.mydeliveries.R;
import com.anythingintellect.mydeliveries.di.ContextModule;
import com.anythingintellect.mydeliveries.util.Navigator;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity  {

    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inject();
        if (savedInstanceState == null) {
            navigator.showDeliveryList();
        }
    }

    private void inject() {
        ((MyDeliveriesApp)getApplication())
                .getAppComponent()
                .plusContextModule(new ContextModule(this))
                .inject(this);
    }

}
