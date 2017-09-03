package com.anythingintellect.mydeliveries.repo;

import com.anythingintellect.mydeliveries.db.LocalStore;
import com.anythingintellect.mydeliveries.model.Delivery;
import com.anythingintellect.mydeliveries.network.MyDeliveriesAPIService;

import io.realm.RealmResults;

/**
 * Created by ishan.dhingra on 03/09/17.
 */

public class DeliveryRepository {

    public DeliveryRepository(LocalStore localStore, MyDeliveriesAPIService apiService) {

    }


    public RealmResults<Delivery> getDeliveries() {
        return null;
    }
}
