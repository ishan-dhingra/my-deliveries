package com.anythingintellect.mydeliveries.db;

import com.anythingintellect.mydeliveries.model.Delivery;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by ishan.dhingra on 03/09/17.
 */

public interface LocalStore {
    void saveDeliveries(List<Delivery> deliveries);

    RealmResults<Delivery> getDeliveries();

    void dispose();
}
