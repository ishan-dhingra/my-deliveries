package com.anythingintellect.mydeliveries.repo;

import com.anythingintellect.mydeliveries.db.LocalStore;
import com.anythingintellect.mydeliveries.db.RealmLocalStore;
import com.anythingintellect.mydeliveries.model.Delivery;
import com.anythingintellect.mydeliveries.model.Location;
import com.anythingintellect.mydeliveries.network.MyDeliveriesAPIService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.realm.RealmResults;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

/**
 * Created by ishan.dhingra on 03/09/17.
 */

public class DeliveryRepositoryTest {

    @Mock
    LocalStore localStore;
    @Mock
    MyDeliveriesAPIService apiService;

    private DeliveryRepository repository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        repository = new DeliveryRepository(localStore, apiService);
    }

    // getDeliveries
    // Should call local store for local deliveries
    @Test
    public void testGetDeliveries_ShouldCallLocalStoreForDeliveries() {

        // TODO: Way to create empty RealmResult for mock
        RealmResults<Delivery> deliveriesResults = repository.getDeliveries();

        verify(localStore, only()).getDeliveries();
        verify(apiService, never()).getDeliveries();
    }

    // fetchAndStoreDeliveries
    // Should fetch from API and store in local store


    // fetchSingleDelivery
    // Should fetch from local store only, as there no API


}
