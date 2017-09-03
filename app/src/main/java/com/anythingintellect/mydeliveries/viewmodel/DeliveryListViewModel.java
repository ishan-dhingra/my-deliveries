package com.anythingintellect.mydeliveries.viewmodel;

import android.databinding.ObservableField;

/**
 * Created by ishan.dhingra on 03/09/17.
 */

public class DeliveryListViewModel {

    private final ObservableField<Boolean> showError;
    private final ObservableField<Boolean> isLoading;

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
}
