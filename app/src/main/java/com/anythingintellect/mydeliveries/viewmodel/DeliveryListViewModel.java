package com.anythingintellect.mydeliveries.viewmodel;

import android.databinding.ObservableField;

import com.anythingintellect.mydeliveries.model.Delivery;

import io.realm.RealmResults;

/**
 * Created by ishan.dhingra on 03/09/17.
 */

public class DeliveryListViewModel {

    private final ObservableField<Boolean> showError;
    private final ObservableField<Boolean> isLoading;
    private RealmResults<Delivery> deliveries;

    public DeliveryListViewModel() {
        this.showError = new ObservableField<>(false);
        this.isLoading = new ObservableField<>(false);
    }


    public ObservableField<Boolean> getShowError() {
        return showError;
    }

    public ObservableField<Boolean> getIsLoading() {
        return isLoading;
    }

    public RealmResults<Delivery> getDeliveries() {
        return deliveries;
    }
}
