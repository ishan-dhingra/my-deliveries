package com.anythingintellect.mydeliveries.util;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.anythingintellect.mydeliveries.R;
import com.anythingintellect.mydeliveries.model.Delivery;
import com.anythingintellect.mydeliveries.view.DeliveryDetailActivity;
import com.anythingintellect.mydeliveries.view.DeliveryDetailFragment;
import com.anythingintellect.mydeliveries.view.DeliveryListFragment;

/**
 * Created by ishan.dhingra on 03/09/17.
 */

public class DefaultNavigator implements Navigator {

    private final Context context;

    public DefaultNavigator(Context context) {
        this.context = context;
    }

    @Override
    public void addDeliveryList() {
        DeliveryListFragment deliveryListFragment = new DeliveryListFragment();
        replaceFragment(R.id.delivery_list_container, deliveryListFragment);
    }

    private void replaceFragment(int container, Fragment fragment) {
        FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(container, fragment)
                .commit();
    }

    @Override
    public void addDeliveryDetail(Delivery delivery) {
        DeliveryDetailFragment deliveryDetailFragment = new DeliveryDetailFragment();
        replaceFragment(R.id.delivery_detail_container, deliveryDetailFragment);
    }

    @Override
    public void openDeliveryDetail(Delivery delivery) {
        Intent detailIntent = new Intent(context, DeliveryDetailActivity.class);
        context.startActivity(detailIntent);
    }

}
