package com.anythingintellect.mydeliveries.db;

import com.anythingintellect.mydeliveries.model.Delivery;

import java.util.List;

import io.realm.Realm;

/**
 * Created by ishan.dhingra on 03/09/17.
 */

public class RealmLocalStore {

    private final Realm realm;

    public RealmLocalStore(Realm realm) {
        this.realm = realm;
    }

    public void saveDeliveries(final List<Delivery> deliveries) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(deliveries);
            }
        });
    }
}
