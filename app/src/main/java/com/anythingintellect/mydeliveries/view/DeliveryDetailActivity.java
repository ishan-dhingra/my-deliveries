package com.anythingintellect.mydeliveries.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anythingintellect.mydeliveries.MyDeliveriesApp;
import com.anythingintellect.mydeliveries.R;
import com.anythingintellect.mydeliveries.di.ContextModule;
import com.anythingintellect.mydeliveries.model.Delivery;
import com.anythingintellect.mydeliveries.model.Location;
import com.anythingintellect.mydeliveries.util.Navigator;

import javax.inject.Inject;

public class DeliveryDetailActivity extends AppCompatActivity {

    public static String KEY_IMG = "img";
    public static String KEY_LAT = "lat";
    public static String KEY_LNG = "lng";
    public static String KEY_DESCRIPTION = "description";
    public static String KEY_ADDRESS = "address";

    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_detail);
        inject();
        if (savedInstanceState == null) {
            navigator.showDeliveryDetail(DeliveryDetailActivity
                    .getDelivery(getIntent().getExtras()));
        }
    }

    private void inject() {
        ((MyDeliveriesApp)getApplication())
                .getAppComponent()
                .plusContextModule(new ContextModule(this))
                .inject(this);
    }

    public static Bundle getParam(Delivery delivery) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_IMG, delivery.getImageUrl());
        bundle.putString(KEY_DESCRIPTION, delivery.getDescription());
        bundle.putString(KEY_ADDRESS, delivery.getLocation().getAddress());
        bundle.putDouble(KEY_LAT, delivery.getLocation().getLat());
        bundle.putDouble(KEY_LNG, delivery.getLocation().getLng());
        return bundle;
    }

    public static Delivery getDelivery(Bundle arguments) {
        Delivery del = new Delivery();
        del.setDescription(arguments.getString(DeliveryDetailActivity.KEY_DESCRIPTION));
        del.setImageUrl(arguments.getString(DeliveryDetailActivity.KEY_IMG));
        Location location = new Location();
        location.setLat(arguments.getDouble(DeliveryDetailActivity.KEY_LAT));
        location.setLng(arguments.getDouble(DeliveryDetailActivity.KEY_LNG));
        location.setAddress(arguments.getString(DeliveryDetailActivity.KEY_ADDRESS));
        del.setLocation(location);
        return del;
    }
}
