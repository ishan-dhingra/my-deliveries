package com.anythingintellect.mydeliveries.viewmodel;

import com.anythingintellect.mydeliveries.model.Delivery;
import com.anythingintellect.mydeliveries.util.Navigator;

/**
 * Created by ishan.dhingra on 04/09/17.
 */

public class DeliveryItemViewModel {

    private Delivery delivery;
    private final Navigator navigator;

    public DeliveryItemViewModel(Navigator navigator) {
        this.navigator = navigator;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public void onClick() {
        navigator.showDeliveryDetail(delivery);
    }
}
