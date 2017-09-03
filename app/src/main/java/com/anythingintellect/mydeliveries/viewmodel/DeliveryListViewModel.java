package com.anythingintellect.mydeliveries.viewmodel;

import android.databinding.ObservableField;

/**
 * Created by ishan.dhingra on 03/09/17.
 */

public class DeliveryListViewModel {
    private ObservableField<Boolean> showError;
    private ObservableField<Boolean> isLoading;

    public ObservableField<Boolean> getShowError() {
        return showError;
    }

    public ObservableField<Boolean> getIsLoading() {
        return isLoading;
    }
}
