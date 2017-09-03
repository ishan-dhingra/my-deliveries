package com.anythingintellect.mydeliveries.repo;

import com.anythingintellect.mydeliveries.model.Delivery;

import java.util.List;

import io.reactivex.Observable;
import io.realm.RealmResults;

/**
 * Created by ishan.dhingra on 03/09/17.
 */

public interface DeliveryRepository {

    RealmResults<Delivery> getDeliveries();
    Observable<List<Delivery>> fetchAndStoreDeliveries();
    void dispose();
}
