package com.anythingintellect.mydeliveries.util;

import com.anythingintellect.mydeliveries.model.Delivery;

/**
 * Created by ishan.dhingra on 03/09/17.
 */

public interface Navigator {
    void addDeliveryList();

    void addDeliveryDetail(Delivery delivery);

    void openDeliveryDetail(Delivery delivery);
}
