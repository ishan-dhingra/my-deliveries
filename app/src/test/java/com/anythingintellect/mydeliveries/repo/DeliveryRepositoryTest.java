package com.anythingintellect.mydeliveries.repo;

import com.anythingintellect.mydeliveries.BaseTest;
import com.anythingintellect.mydeliveries.db.LocalStore;
import com.anythingintellect.mydeliveries.model.Delivery;
import com.anythingintellect.mydeliveries.network.MyDeliveriesAPIService;
import com.anythingintellect.mydeliveries.util.MockData;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import io.reactivex.Observable;
import io.realm.RealmResults;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ishan.dhingra on 03/09/17.
 */

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class DeliveryRepositoryTest extends BaseTest {

    @Mock
    LocalStore localStore;
    @Mock
    MyDeliveriesAPIService apiService;

    private DeliveryRepositoryImpl repository;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        repository = new DeliveryRepositoryImpl(localStore, apiService);
        MockData.init();
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
    // Should fetch from API and store in local store; return API Observable
    @Test
    public void testFetchAndStoreDeliveries_shouldFetchFromAPIAndStoreInLocalStore() {
        List<Delivery> responseList = MockData.getNDeliveries(5);
        Observable<List<Delivery>> response = Observable.just(responseList);
        when(apiService.getDeliveries()).thenReturn(response);

        Observable<List<Delivery>> observable = repository.fetchAndStoreDeliveries();

        observable.blockingFirst();

        verify(apiService, only()).getDeliveries();
        verify(localStore, only()).saveDeliveries(ArgumentMatchers.<Delivery>anyList());


    }



}
