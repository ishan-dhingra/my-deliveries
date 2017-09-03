package com.anythingintellect.mydeliveries.db;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.anythingintellect.mydeliveries.model.Delivery;
import com.anythingintellect.mydeliveries.model.Location;
import com.anythingintellect.mydeliveries.util.MockData;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by ishan.dhingra on 03/09/17.
 */

@RunWith(AndroidJUnit4.class)
public class RealmLocalStoreTest {

    private RealmLocalStore localStore;
    private Realm realm;

    @Before
    public void setup() {
        MockData.init();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RealmConfiguration.Builder configBuilder = new RealmConfiguration.Builder();
                configBuilder.inMemory();
                realm = Realm.getInstance(configBuilder.build());
                localStore = new RealmLocalStore(realm);
            }
        });

    }

    private void runOnUiThread(Runnable runnable) {
        InstrumentationRegistry
                .getInstrumentation()
                .runOnMainSync(runnable);
    }

    // Should save deliveries
    @Test
    public void testSaveDeliveries_ShouldSaveDeliveriesToRealm() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final List<Delivery> deliveries = MockData.getNDeliveries(5);

                localStore.saveDeliveries(deliveries);

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        RealmResults<Delivery> realmResults = realm.where(Delivery.class).findAll();
                        assertNotEquals(null, realmResults);
                        assertEquals(deliveries.size(), realmResults.size());
                    }
                });
            }
        });

    }
    // Should return saved deliveries
    @Test
    public void testGetDeliveries_ShouldReturnSavedDeliveries() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final List<Delivery> deliveries = MockData.getNDeliveries(5);
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealmOrUpdate(deliveries);
                    }
                });

                RealmResults<Delivery> deliveriesResults = localStore.getDeliveries();

                assertNotEquals(null, deliveriesResults);
                deliveriesResults.load();
                assertEquals(deliveries.size(), deliveriesResults.size());

            }
        });
    }

    // Should return deliveries in sorted by id order
    @Test
    public void testGetDeliveries_ShouldReturnDeliveriesSortedByIdAscending() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final List<Delivery> deliveries = MockData.getNDeliveries(5);
                Delivery firstDeliver = new Delivery();
                firstDeliver.setDescription("Test");
                firstDeliver.setId(109L);
                firstDeliver.setLocation(new Location());
                deliveries.add(0, firstDeliver);
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealmOrUpdate(deliveries);
                    }
                });

                RealmResults<Delivery> realmResults = localStore.getDeliveries();
                realmResults.load();

                assertEquals(deliveries.size(), realmResults.size());
                Delivery last = realmResults.get(0);
                boolean isSorted = true;
                for (int i = 1; i < realmResults.size(); i++) {
                    Delivery current = realmResults.get(i);
                    if (last.getId() > current.getId()) {
                        isSorted = false;
                        break;
                    }
                    last = current;
                }
                assertEquals(true, isSorted);
            }
        });
    }


    // Should close realm on dispose call
    @Test
    public void testDispose_ShouldCloseRealmOnDisposeCall() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                localStore.dispose();
                assertEquals(true,realm.isClosed());
            }
        });
    }


}
