package com.anythingintellect.mydeliveries.viewmodel;

import android.databinding.ObservableField;

import com.anythingintellect.mydeliveries.BaseTest;
import com.anythingintellect.mydeliveries.model.Delivery;
import com.anythingintellect.mydeliveries.repo.DeliveryRepository;
import com.anythingintellect.mydeliveries.util.MockData;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.realm.RealmResults;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ishan.dhingra on 03/09/17.
 */

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class DeliveryListViewModelTest extends BaseTest {

    @Mock
    DeliveryRepository repository;
    private DeliveryListViewModel viewModel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MockData.init();
        viewModel = new DeliveryListViewModel(repository);
    }


    // Should hide error and loader by default
    @Test
    public void shouldHideErrorAndLoaderByDefault() {
        ObservableField<Boolean> showError = viewModel.getShowError();
        ObservableField<Boolean> isLoading = viewModel.getIsLoading();

        assertNotEquals(null, showError);
        assertNotEquals(null, isLoading);
        assertEquals(false, showError.get());
        assertEquals(false, isLoading.get());
    }


    // syncDeliveries
    // Should call repo.loadDeliveriesAndSave
    @Test
    public void testSyncDeliveries_ShouldCallRepoLoadDeliveriesAndSave() {
        // Need to reset, as one of the method is been called in constructor
        reset(repository);
        List<Delivery> responseList = MockData.getNDeliveries(5);
        when(repository.fetchAndStoreDeliveries())
                .thenReturn(Observable.just(responseList));

        viewModel.syncDeliveries();

        verify(repository).fetchAndStoreDeliveries();

    }


    // Should show/hide loader
    @Test
    public void testSyncDeliveries_ShouldShowHideLoader() {
        List<Delivery> responseList = MockData.getNDeliveries(5);
        BehaviorSubject<List<Delivery>> responseSubject = BehaviorSubject.create();
        when(repository.fetchAndStoreDeliveries())
                .thenReturn(responseSubject);

        viewModel.syncDeliveries();

        assertEquals(true, viewModel.getIsLoading().get());
        responseSubject.onNext(responseList);
        responseSubject.onComplete();
        assertEquals(false, viewModel.getIsLoading().get());
    }

    // Should not do another request in case already loading
    @Test
    public void testSyncDeliveries_ShouldNotDoAnotherRequestIfOneAlreadyLoading() {
        reset(repository);
        List<Delivery> responseList = MockData.getNDeliveries(5);
        BehaviorSubject<List<Delivery>> responseSubject = BehaviorSubject.create();
        when(repository.fetchAndStoreDeliveries())
                .thenReturn(responseSubject);

        // First request
        viewModel.syncDeliveries();

        reset(repository);

        when(repository.fetchAndStoreDeliveries())
                .thenReturn(responseSubject);

        // Second Request
        viewModel.syncDeliveries();

        verify(repository, never()).fetchAndStoreDeliveries();


    }

    // Should show error and hide loader in case of API error with empty local cache

    // Should show error and hide loader toast in case of API with valid local cache

    // dispose
    // Should dispose local store


}