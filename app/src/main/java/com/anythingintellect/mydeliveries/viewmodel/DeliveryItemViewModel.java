package com.anythingintellect.mydeliveries.viewmodel;

import android.databinding.ObservableField;

import com.anythingintellect.mydeliveries.model.Delivery;
import com.anythingintellect.mydeliveries.util.OnDeliverySelectedListener;

/**
 * Created by ishan.dhingra on 04/09/17.
 */

public class DeliveryItemViewModel {

    private Delivery delivery;
    private final OnDeliverySelectedListener onDeliverySelectedListener;

    public DeliveryItemViewModel(OnDeliverySelectedListener onDeliverySelectedListener) {
        this.onDeliverySelectedListener = onDeliverySelectedListener;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public void onClick() {
        onDeliverySelectedListener.onDeliverySelected(delivery);
    }
}
