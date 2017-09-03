package com.anythingintellect.mydeliveries.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anythingintellect.mydeliveries.MyDeliveriesApp;
import com.anythingintellect.mydeliveries.R;
import com.anythingintellect.mydeliveries.di.ContextModule;
import com.anythingintellect.mydeliveries.model.Delivery;
import com.anythingintellect.mydeliveries.util.Navigator;
import com.anythingintellect.mydeliveries.util.OnDeliverySelectedListener;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements OnDeliverySelectedListener {

    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inject();
        if (savedInstanceState == null) {
            navigator.addDeliveryList();
        }
    }

    private void inject() {
        ((MyDeliveriesApp)getApplication())
                .getAppComponent()
                .plusContextModule(new ContextModule(this))
                .inject(this);
    }

    @Override
    public void onDeliverySelected(Delivery delivery) {
        if (findViewById(R.id.delivery_detail_container) == null) {
            navigator.openDeliveryDetail(delivery);
        } else {
            navigator.addDeliveryDetail(delivery);
        }
    }
}
