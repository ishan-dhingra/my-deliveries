package com.anythingintellect.mydeliveries.repo;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.anythingintellect.mydeliveries.db.LocalStore;
import com.anythingintellect.mydeliveries.model.Delivery;
import com.anythingintellect.mydeliveries.network.MyDeliveriesAPIService;

import io.realm.RealmResults;

/**
 * Created by ishan.dhingra on 03/09/17.
 */

public class DeliveryRepository {

    LocalStore localStore;
    MyDeliveriesAPIService apiService;

    public DeliveryRepository(@NonNull LocalStore localStore,
                              @NonNull MyDeliveriesAPIService apiService) {
        this.localStore = localStore;
        this.apiService = apiService;
    }


    public RealmResults<Delivery> getDeliveries() {
        return localStore.getDeliveries();
    }

    public void fetchAndStoreDeliveries() {

    }
}
