package com.anythingintellect.mydeliveries.viewmodel;

import android.databinding.ObservableField;

import com.anythingintellect.mydeliveries.BaseTest;
import com.anythingintellect.mydeliveries.model.Delivery;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.Observable;
import io.realm.RealmResults;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by ishan.dhingra on 03/09/17.
 */

public class DeliveryListViewModelTest extends BaseTest {

    private DeliveryListViewModel viewModel;

    @Before
    public void setup() {
        viewModel = new DeliveryListViewModel();
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


    // Should have non-null RealmResult
    @Test
    public void shouldHaveNonNullRealmResultDeliveries() {
        RealmResults<Delivery> deliveries = viewModel.getDeliveries();

        assertNotEquals(null, deliveries);
    }

    // loadDeliveries
    // Should call repo.loadDeliveriesAndSave

    // Should show/hide loaded

    // Should show error in case of API error with empty local cache

    // Should show error toast in case of API with valid local cache


}
